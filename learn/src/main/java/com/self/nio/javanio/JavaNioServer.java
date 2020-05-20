package com.self.nio.javanio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Objects;

/**
 * 启动起来之后，看到的效果：确实是没有阻塞，一直在循环，所以叫nio
 *
 * @author shichen
 * @create 2020/5/20
 * @desc
 */
public class JavaNioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8080));


        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (Objects.nonNull(socketChannel)) {
                System.out.println("有连接：" + socketChannel.toString());
            } else {
                System.out.println("没有连接到来");
            }
        }
    }

}
