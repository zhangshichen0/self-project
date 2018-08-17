package com.self.lock.reentrant_lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shichen
 * @create 2018/7/17
 * @desc
 */
public class ReentrantLockTest {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("获取锁成功 " + Thread.currentThread().getName());
                    Thread.sleep(100000);
                    lock.unlock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("获取锁成功 " + Thread.currentThread().getName());
                    Thread.sleep(100000);
                    lock.unlock();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "2");

        thread1.start();
        thread2.start();

        Thread.currentThread().join();
    }

}
