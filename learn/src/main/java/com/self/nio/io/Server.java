package com.self.nio.io;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shichen
 * @create 2018/8/22
 * @desc
 */
public class Server {

    private static AtomicInteger num = new AtomicInteger();

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(10086);
            while (true) {
                Socket client = serverSocket.accept();
                Runnable clientThread = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            byte[] read = new byte[1024];

                            OutputStream outputStream = client.getOutputStream();
                            outputStream.write("xx".getBytes(Charsets.UTF_8));
                            InputStream inputStream = client.getInputStream();
                            inputStream.read(read);

                            System.out.println(new String(read));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Thread thread = new Thread(clientThread, String.valueOf(num.incrementAndGet()));
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
