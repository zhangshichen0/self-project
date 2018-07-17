package com.self.lock;

import java.util.concurrent.TimeUnit;
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
                    if(lock.tryLock(10, TimeUnit.SECONDS)) {
                       System.out.println("获取锁成功 " + Thread.currentThread().getId());
                       Thread.sleep(1000);
                       lock.unlock();
                    } else {
                        System.out.println("获取所失败 " + Thread.currentThread().getId());
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(lock.tryLock(10, TimeUnit.SECONDS)) {
                        System.out.println("获取锁成功 " + Thread.currentThread().getId());
                        Thread.sleep(1000);
                        lock.unlock();
                    } else {
                        System.out.println("获取所失败 " + Thread.currentThread().getId());
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread1.start();
        thread2.start();

        Thread.currentThread().join();
    }

}
