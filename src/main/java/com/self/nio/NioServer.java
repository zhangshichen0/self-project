package com.self.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shichen
 * @create 2018/5/1
 * @desc
 */
public class NioServer {

    private ServerSocketChannel serverSocketChannel;
    private int port = 8080;
    private Selector selector;

    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer revBuffer = ByteBuffer.allocate(1024);

    private int clientId = 10000;

    private Map<SelectionKey, String> msgMap = new HashMap<>(100);
    private Map<SocketChannel, Integer> clientMap = new HashMap<>(100);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public NioServer(int port) throws IOException {
        this.port = port;
        serverSocketChannel = ServerSocketChannel.open();

        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));


        //获得注册器
        selector = Selector.open();

        //将serverSocketChannel注册到注册器上，并设置为接收客户端连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 监听selector上的事件
     */
    private void listener() throws IOException {
        while (true) {
            int eventCount = selector.select();
            if(eventCount == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                this.process(key);
            }
        }
    }

    /**
     * 处理接收到的事件
     * 每一次接收到消息后，都需要手动切换通道为可读还是可写
     * @param selectionKey
     */
    private void process(SelectionKey selectionKey){
        if (null == selectionKey) {
            return;
        }
        SocketChannel clientChannel = null;
        try {
            if (selectionKey.isValid() && selectionKey.isAcceptable()){
                clientChannel = serverSocketChannel.accept();
                if (null != clientChannel) {
                    // 设置成非阻塞
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);

                    ++ clientId;
                }
            } else if(selectionKey.isValid() && selectionKey.isReadable()){
                revBuffer.clear();
                clientChannel = (SocketChannel)selectionKey.channel();

                int len = clientChannel.read(revBuffer);
                if (len > 0) {
                    revBuffer.flip();
                    String msg = new String(revBuffer.array(), 0, len);
                    if (Objects.nonNull(msg)) {
                        System.out.println("接收到客户端请求，线程：" + Thread.currentThread().getId() + ",msg:" + msg);
                        msgMap.put(selectionKey, msg);
                        clientMap.put(clientChannel, clientId);

                        //切换通道可写
                        clientChannel.register(selector, SelectionKey.OP_WRITE);
                    }
                }
            } else if (selectionKey.isValid() && selectionKey.isWritable()) {
                if (!msgMap.containsKey(selectionKey)) {
                    return;
                }
                clientChannel = (SocketChannel) selectionKey.channel();
                //写消息处理
                sendBuffer.clear();
                System.out.println("回应到客户端请求，线程：" + Thread.currentThread().getId());
                String msg = msgMap.get(selectionKey) + ",您好，server端处理了你的请求";
                sendBuffer.put(msg.getBytes("UTF-8"));
                sendBuffer.flip();

                clientChannel.write(sendBuffer);

                //切换通道为可读
                clientChannel.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            try {
                selectionKey.cancel();
                clientChannel.socket().close();
                clientChannel.close();
                System.out.println(simpleDateFormat.format(new Date()) + "客户端：" + clientMap.get(clientChannel) + "下线，处理的线程为：" + Thread.currentThread().getId());
                msgMap.remove(selectionKey);
                clientMap.remove(clientChannel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new NioServer(8080).listener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
