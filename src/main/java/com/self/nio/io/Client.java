package com.self.nio.io;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author shichen
 * @create 2018/8/22
 * @desc
 */
public class Client {

    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(10086));
            if (socket.isConnected()) {
                InputStream inputStream = socket.getInputStream();

                byte[] read = new byte[1024];

                inputStream.read(read);
                System.out.println(new String(read));

                OutputStream outputStream = socket.getOutputStream();

                while (true) {
                    outputStream.write("hello".getBytes(Charsets.UTF_8));
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
