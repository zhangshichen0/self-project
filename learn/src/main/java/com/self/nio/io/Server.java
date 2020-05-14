package com.self.nio.io;

import com.google.common.base.Charsets;

import java.io.*;
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
                            OutputStream outputStream = client.getOutputStream();

                            //向外写数据
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                            bufferedWriter.write("xx");

                            //读取网络数据
                            InputStream inputStream = client.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            while (true) {
                                String content = bufferedReader.readLine();
                                if (content.equals("end")) {
                                    break;
                                }
                                System.out.println(content);
                            }
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
