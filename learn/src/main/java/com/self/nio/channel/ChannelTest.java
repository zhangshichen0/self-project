package com.self.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author shichen
 * @create 2018/4/30
 * @desc
 */
public class ChannelTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("/Users/shichen/program/worker/self-project/src/main/resources/channel.txt", "rw");
        FileChannel fileChannel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int byteRead = fileChannel.read(buffer);

        if (byteRead == -1) {
            buffer.clear();
            buffer.put("xxxxxx".getBytes("utf-8"));
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }
        while (byteRead != -1) {
            System.out.println("read count:" + byteRead);
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.println("content:" + (char)buffer.get());
            }

            buffer.clear();
            byteRead = fileChannel.read(buffer);
        }

        fileChannel.close();
        file.close();
    }

}
