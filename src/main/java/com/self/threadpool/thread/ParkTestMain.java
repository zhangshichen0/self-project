package com.self.threadpool.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author shichen
 * @create 2018/8/14
 * @desc
 */
public class ParkTestMain {

    public static void main(String[] args) throws InterruptedException {
        Syc syc = new ParkTestMain.Syc();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程是否被中断：" + syc.parkAndCheckInterrupt());
            }
        };

        Thread thread = new Thread(runnable, "1");
        thread.start();

        Thread.sleep(10000);
        System.out.println("线程:" + Thread.currentThread().getName() + "休眠10s后启动，并唤醒waiting的线程");
        syc.unpark(thread);
    }

    static class Syc {
        public final boolean parkAndCheckInterrupt() {
            LockSupport.park(this);
            System.out.println("hh");
            return Thread.interrupted();
        }

        /**
         * 终止线程等待【waiting】
         */
        public final void unpark(Thread thread) {
            LockSupport.unpark(thread);
        }
    }

}
