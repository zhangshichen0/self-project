package com.self.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * 基于reactor设计，采用单个线程处理事件
 *
 关于Reactor模式的一些概念：

 Reactor：负责响应IO事件，当检测到一个新的事件，将其发送给相应的Handler去处理。

 Handler：负责处理非阻塞的行为，标识系统管理的资源；同时将handler与事件绑定。

 Reactor为单个线程，需要处理accept连接，同时发送请求到处理器中。

 由于只有单个线程，所以处理器中的业务需要能够快速处理完。
 *
 * @author shichen
 * @create 2018/5/23
 * @desc
 */
public class BasicReactorServer {

    class Reactor implements Runnable {
        private ServerSocketChannel serverSocketChannel;
        private Selector selector;

        public Reactor(int port) throws IOException {
            serverSocketChannel = ServerSocketChannel.open();
            //绑定接受连接的端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            //设置非阻塞
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();
            //将serverSocketChannel注册到selector上
            SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            sk.attach(new Acceptor());
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    int count = selector.select();
                    if (count > 0) {
                        Set<SelectionKey> set = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = set.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            set.remove(selectionKey);
                            this.dispatcher(selectionKey);
                        }
                        set.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 分发事件
         *
         * @param selectionKey
         */
        private void dispatcher(SelectionKey selectionKey) {
            Runnable runnable = (Runnable) selectionKey.attachment();
            if (null != runnable) {
                runnable.run();
            }
        }

        class Acceptor implements Runnable {

            @Override
            public void run() {
                try {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if (null != socketChannel) {
                        new Handler(selector, socketChannel);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 读写事件都在这个类中执行
     */
    class Handler implements Runnable {
        private static final int READING = 0, SENDING = 1;
        private int state = READING;
        private ByteBuffer receive = ByteBuffer.allocate(1024);
        private ByteBuffer send = ByteBuffer.allocate(1024);
        private SocketChannel socketChannel;
        private SelectionKey sk;

        public Handler(Selector sel, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;

            this.socketChannel.configureBlocking(false);
            //socketChannel注册读取事件
            sk = this.socketChannel.register(sel, SelectionKey.OP_READ);
            sk.attach(this);

            sel.wakeup();
        }

        @Override
        public void run() {
            //读取数据
            if (this.state == READING) {
                read();
            }

            if (this.state == SENDING) {
                send();
            }
        }

        public boolean inputIsComplete() {
            return true;
        }

        public boolean outputIsComplete() {
            return true;
        }

        public void process() {

        }

        public void read() {
            try {
                socketChannel.read(receive);
                if (inputIsComplete()) {
                    process();
                    state = SENDING;
                    sk.interestOps(SelectionKey.OP_WRITE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send() {
            try {
                socketChannel.write(send);
                if (outputIsComplete()) {
                    sk.cancel();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //上面 的实现用Handler来同时处理Read和Write事件, 所以里面出现状态判断
    //我们可以用State-Object pattern来更优雅的实现
    class Handler_0 {

        private SelectionKey sk;
        private SocketChannel socketChannel;

        public Handler_0 (Selector selector, SocketChannel socketChannel) throws IOException {
            socketChannel.configureBlocking(false);

            this.socketChannel = socketChannel;
            sk = this.socketChannel.register(selector, SelectionKey.OP_READ);
            sk.attach(new Reader());
        }

        class Sender implements Runnable {

            private ByteBuffer sender = ByteBuffer.allocate(1024);

            @Override
            public void run() {
                try {
                    socketChannel.write(sender);
                    if (outputIsComplete()) {
                        sk.cancel();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public boolean outputIsComplete() {
                return true;
            }

        }

        class Reader implements Runnable {

            private ByteBuffer reader = ByteBuffer.allocate(1024);

            @Override
            public void run() {
                try {
                    socketChannel.read(reader);
                    if (inputIsComplete()) {
                        sk.interestOps(SelectionKey.OP_WRITE);
                        sk.attach(new Sender());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public boolean inputIsComplete() {
                return true;
            }
        }

    }

}
