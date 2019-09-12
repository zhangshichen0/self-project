package com.self.mini_netty;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shichen
 * @create 2018-12-26
 * @desc
 */
public class MiniNettyMainTest {

    private static AtomicInteger threadNum = new AtomicInteger();

    public static void main(String[] args) {

        MiniNioEventLoopGroup miniNioEventLoopGroup = new MiniNioEventLoopGroup(5, r -> new Thread(r, "Mini_Thread_" + threadNum.incrementAndGet()));
        try {
            MiniServerBootstrap miniServerBootstrap = new MiniServerBootstrap(miniNioEventLoopGroup);
            miniServerBootstrap.bind("127.0.0.1", 8181);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
