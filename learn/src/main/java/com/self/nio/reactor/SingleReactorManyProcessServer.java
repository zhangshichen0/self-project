package com.self.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.Time;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author shichen
 * @create 2018/5/24
 * @desc
 */
public class SingleReactorManyProcessServer implements Runnable {

    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;

    public SingleReactorManyProcessServer(int port) throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.bind(new InetSocketAddress(port));
        this.serverSocketChannel.configureBlocking(false);

        this.selector = Selector.open();
        SelectionKey sk = this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                int count = this.selector.select();
                if (count > 0) {
                    Set<SelectionKey> eventSet = this.selector.selectedKeys();
                    Iterator<SelectionKey> iterator = eventSet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey event = iterator.next();
                        this.dispatcher(event);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 事件分发
     *
     * @param event
     */
    private void dispatcher(SelectionKey event) {
        Runnable r = (Runnable) event.attachment();
        if (null != r) {
            r.run();
        }

    }


    /**
     * 接受连接处理器
     */
    final class Acceptor implements Runnable {

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


    /**
     * 读写事件处理
     */
    final class Handler implements Runnable {

        private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2
                , 100000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread();
            }
        });

        private final SocketChannel socketChannel;
        private SelectionKey selectionKey;

        private final ByteBuffer RECEIVE = ByteBuffer.allocate(1024);
        private final ByteBuffer SEND = ByteBuffer.allocate(1024);
        private static final int READING = 0, SENDING = 1, PROCESSING = 2;
        private int state = READING;

        /**
         *
         * @param sel 选择器
         * @param socketChannel 客户端连接
         */
        public Handler(Selector sel, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            this.socketChannel.configureBlocking(false);

            //注册读事件
            selectionKey = this.socketChannel.register(sel, SelectionKey.OP_READ);
            selectionKey.attach(this);
        }

        /**
         * 处理读
         */
        synchronized void read() {
            try {
                socketChannel.read(RECEIVE);
                if (inputIsComplete()) {
                    state = PROCESSING;
                    //使用线程pool异步执行
                    threadPoolExecutor.execute(new HandlerProcessor(this));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean inputIsComplete() {
            return true;
        }

        synchronized void processAndHandOff() {
            process();
            // or rebind attachment
            state = SENDING;
            //process完,开始等待write事件
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }

        private void process() {
        }

        @Override
        public void run() {
            if (state == READING) {
                read();
            }
            if (state == SENDING) {

            }
        }
    }

    /**
     *
     */
    private class HandlerProcessor implements Runnable {

        private Handler handler;

        public HandlerProcessor(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            this.handler.processAndHandOff();
        }
    }
}
