package com.self.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * @author shichen
 * @create 2018/5/1
 * @desc
 */
public class NioClient {

    private SocketChannel socketChannel;
    private int port = 8080;
    private Selector selector;

    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer revBuffer = ByteBuffer.allocate(1024);

    public NioClient(int port) throws IOException {
        this.port = port;
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        socketChannel.connect(new InetSocketAddress(port));

        selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }

    private void session() throws IOException {
        //判断和服务端是否建立完连接
        if(socketChannel.isConnectionPending()) {
            socketChannel.finishConnect();
            socketChannel.register(selector, SelectionKey.OP_WRITE);
            System.out.println("系统提示：您已经连接上服务器，请发送消息");
        }
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            if ("".equals(msg)) {
                continue;
            }
            if (Objects.equals("exit", msg)) {
                System.exit(0);
            }
            process(msg);
        }
    }

    private void process(String name) throws IOException {
        boolean isWait = true;
        while (isWait) {
            int eventCount = selector.select();
            if (eventCount == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                try {
                    if (key.isWritable()) {
                        sendBuffer.clear();
                        sendBuffer.put(name.getBytes("UTF-8"));
                        sendBuffer.flip();
                        socketChannel.write(sendBuffer);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        revBuffer.clear();
                        int len = socketChannel.read(revBuffer);
                        if (len > 0) {
                            revBuffer.flip();
                            String msg1 = new String(revBuffer.array(), 0, len);
                            System.out.println("服务端回应消息：" + msg1);
                        }
                        socketChannel.register(selector, SelectionKey.OP_WRITE);
                        isWait = false;
                    }
                } catch (IOException e) {
                    key.cancel();
                    socketChannel.socket().close();
                    socketChannel.close();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            new NioClient(8181).session();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
