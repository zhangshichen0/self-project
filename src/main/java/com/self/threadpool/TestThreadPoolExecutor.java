package com.self.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author shichen
 * @create 2018/3/1
 * @desc
 */
public class TestThreadPoolExecutor {

    /**
     * thread counter
     */
    public final static AtomicLong THREAD_ID = new AtomicLong(0);

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 2000, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10), r -> new Thread(r, "task-thread-" + THREAD_ID.incrementAndGet()), new MyHandle());

    static{
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i ++) {
            threadPoolExecutor.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
    }


    public static class MyHandle implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("被拒绝");
        }
    }
}
