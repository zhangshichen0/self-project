package com.self.mini_netty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author shichen
 * @create 2018-12-25
 * @desc
 */
public class MiniNioEventLoop {

    private Selector selector;
    private SelectorProvider selectorProvider;
    private Thread thread;

    public MiniNioEventLoop(SelectorProvider selectorProvider, ThreadFactory threadFactory) {
        this.selectorProvider = selectorProvider;
        this.selector = openSelector();
        this.thread = threadFactory.newThread(() ->{
            MiniNioEventLoop.this.run();
        });
    }

    /**
     * 打开selector
     */
    private Selector openSelector() {
        final Selector selector;
        try {
            selector = this.selectorProvider.openSelector();
        } catch (IOException e) {
            throw new RuntimeException("failed to open a new selector", e);
        }
        return selector;
    }

    /**
     * 注册channel到selector上
     *
     * @param ch
     * @return
     */
    public boolean registerChannel(SelectableChannel ch, int interestOps) {
        if (Objects.isNull(ch)) {
            throw new NullPointerException("channel");
        }

        if (interestOps == 0) {
            throw new IllegalArgumentException("interestOps must be non-zero.");
        }
        if ((interestOps & ~ch.validOps()) != 0) {
            throw new IllegalArgumentException(
                    "invalid interestOps: " + interestOps + "(validOps: " + ch.validOps() + ')');
        }

        try {
            ch.configureBlocking(false);
            ch.register(this.selector, interestOps);
        } catch (Exception e) {
            throw new RuntimeException("register error");
        }
        return true;
    }

    public void run() {
        while (true) {
            try {
                int select = this.selector.select(1000);
                if (select > 0) {
                    Set<SelectionKey> keys = this.selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = keys.iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey selectionKey = keyIterator.next();
                        keyIterator.remove();
                        if (selectionKey.isReadable()) {
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            int count = socketChannel.read(byteBuffer);
                            if (count > 0) {
                                byteBuffer.flip();
                                System.out.println(byteBuffer.getInt());
                                byteBuffer.clear();
                            } else {
                                socketChannel.close();
                            }
                        }
                    }
                } else {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (this.thread.isAlive() || this.thread.isInterrupted()) {
            return;
        }
        this.thread.start();
    }

}
