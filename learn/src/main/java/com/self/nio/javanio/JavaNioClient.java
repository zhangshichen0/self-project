package com.self.nio.javanio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author shichen
 * @create 2020/5/20
 * @desc
 */
public class JavaNioClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(8080));

        while (true) {
            if (socketChannel.finishConnect()) {
                System.out.println("连接成功");
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byteBuffer.put("客户端1".getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                break;
            } else {
                System.out.println("xxxxx");
            }
        }
        //读缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            int length = socketChannel.read(buffer);
            if (length > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println(new String(buffer.array()));
                }
            } else {
                System.out.println("没有数据到来");
            }
        }
    }

}
