package com.self.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author shichen
 * @create 2018/5/24
 * @desc
 *
 * mainReactor专门检测连接
 * subReactor专门处理事件
 */
public class MainReactorSubReactorServer implements Runnable {
    private final ServerSocketChannel serverSocketChannel;
    private final Selector mainSelector;


    public MainReactorSubReactorServer(int port, int subSelectorNum) throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.bind(new InetSocketAddress(port));
        this.serverSocketChannel.configureBlocking(false);

        this.mainSelector = Selector.open();
        SelectionKey sk = this.serverSocketChannel.register(this.mainSelector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor(subSelectorNum));
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                int count = this.mainSelector.select();
                if (count > 0) {
                    Set<SelectionKey> eventSet = this.mainSelector.selectedKeys();
                    Iterator<SelectionKey> iterator = eventSet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey event = iterator.next();
                        Runnable r = (Runnable) event.attachment();
                        if (null != r) {
                            r.run();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    public class Acceptor implements Runnable{
        private final Selector[] subSelector;
        private final SubReactor[] subReactors;
        private int next = 0;

        public Acceptor(int subSelectorNum) throws IOException {
            this.subSelector = new Selector[subSelectorNum];
            this.subReactors = new SubReactor[subSelectorNum];
            for (int i = 0; i < subSelectorNum; i ++) {
                subSelector[i] = Selector.open();
                SubReactor subReactor = new SubReactor(subSelector[i]);
                subReactors[i] = subReactor;
                new Thread(subReactors[i]).start();
            }
        }

        @Override
        public synchronized void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (null != socketChannel) {
                    SubReactor subReactor = subReactors[next];
                    subReactor.add(socketChannel);
                    if (++next >= subSelector.length) {
                        next = 0;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 子reactor
     */
    private class SubReactor implements Runnable {

        private final Selector selector;

        public SubReactor(Selector selector) {
            this.selector = selector;
        }

        public void add(SocketChannel socketChannel) throws IOException {
            socketChannel.configureBlocking(false);
            SelectionKey sk = socketChannel.register(this.selector, SelectionKey.OP_READ);
            sk.attach(new Handler(this.selector, socketChannel));
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    int count = this.selector.select();
                    if (count > 0) {
                        Set<SelectionKey> subEvent = this.selector.selectedKeys();
                        Iterator<SelectionKey> iterator = subEvent.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey event = iterator.next();
                            Runnable run = (Runnable) event.attachment();
                            if (null != run) {
                                run.run();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 处理读写事件,使用线程池处理读到的数据
     */
    private class Handler implements Runnable {


        public Handler(Selector selector, SocketChannel socketChannel) {

        }

        @Override
        public void run() {

        }
    }
}
