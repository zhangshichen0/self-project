package com.self.mini_netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @author shichen
 * @create 2018-12-26
 * @desc
 */
public class MiniNettyClientMainTest {


    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    public MiniNettyClientMainTest(int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        socketChannel.connect(new InetSocketAddress("127.0.0.1", port));

        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        this.session(socketChannel, selector);
    }

    private void session(SocketChannel socketChannel, Selector selector) throws IOException {
        //判断和服务端是否建立完连接
        if(socketChannel.isConnectionPending()) {
            socketChannel.finishConnect();
            socketChannel.register(selector, SelectionKey.OP_WRITE);
            System.out.println("系统提示：您已经连接上服务器，请发送消息");

            for (int i = 10; i < 20; i ++) {
                sendBuffer.clear();
                sendBuffer.putInt(i);
                sendBuffer.flip();
                socketChannel.write(sendBuffer);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            socketChannel.close();
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        new MiniNettyClientMainTest(8181);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
