package com.self.nio.reactor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统的socket实现--阻塞模式
 *
 * 阻塞的原因：
 *
 1、serversocket的accept方法，阻塞等待client连接，直到client连接成功。

 2、线程从socket inputstream读入数据，会进入阻塞状态，直到全部数据读完。

 3、线程向socket outputstream写入数据，会阻塞直到全部数据写完。
 *
 * @author shichen
 * @create 2018/5/22
 * @desc
 */
public class TraditionalSocketIo {

    /**
     * 服务器实现
     */
    class Server implements Runnable {
        private ServerSocket serverSocket = null;

        public Server(int port) throws IOException {
            serverSocket = new ServerSocket(port);
        }


        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Socket socket = serverSocket.accept();
                    new Thread(new Handler(socket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    class Handler implements Runnable {

        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                if (null != socket) {
                    byte[] input = new byte[1024];
                    socket.getInputStream().read(input);

                    byte[] output = this.process(input);
                    socket.getOutputStream().write(output);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public byte[] process(byte[] input) throws UnsupportedEncodingException {
            return "write".getBytes("Utf-8");
        }
    }

}
