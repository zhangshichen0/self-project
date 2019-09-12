package com.self.mini_netty;

import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 用于创建MiniNioEventLoop对象
 *
 * @author shichen
 * @create 2018-12-25
 * @desc
 */
public class MiniNioEventLoopGroup {

    private AtomicInteger childMiniNioEventLoopIndex = new AtomicInteger();
    private MiniNioEventLoop[] miniNioEventLoops;

    private MiniNioEventLoopChooser miniNioEventLoopChooser;

    /**
     *
     * @param nThreads 线程数
     */
    public MiniNioEventLoopGroup(int nThreads, ThreadFactory threadFactory) {

        if (nThreads <= 0) {
            throw new IllegalArgumentException("need to granter than 0");
        }

        this.miniNioEventLoops = new MiniNioEventLoop[nThreads];
        for (int i = 0; i < nThreads; i ++) {
            this.miniNioEventLoops[i] = new MiniNioEventLoop(SelectorProvider.provider(), threadFactory);
            this.miniNioEventLoops[i].start();
        }
        this.miniNioEventLoopChooser = new GenericEventExecutorChooser();
    }

    /**
     * 返回
     *
     * @return
     */
    public MiniNioEventLoop next() {
        return this.miniNioEventLoopChooser.chooser();
    }

    private interface MiniNioEventLoopChooser {

        /**
         * 返回miniNioEventLoop
         *
         * @return
         */
        MiniNioEventLoop chooser();
    }

    private class GenericEventExecutorChooser implements MiniNioEventLoopChooser {

        @Override
        public MiniNioEventLoop chooser() {
            int i = Math.abs(childMiniNioEventLoopIndex.getAndIncrement() % miniNioEventLoops.length);
            System.out.println(i);
            return miniNioEventLoops[i];
        }
    }
}
