package com.self.lock.reentrant_lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shichen
 * @create 2018/7/17
 * @desc
 */
public class ReentrantLockTest {

    /**
     * 步骤【非公平锁为例】：
     * 1.线程1尝试获取锁，由于现在锁空闲，线程1获取成功，处理业务
     * 2.此时线程2尝试获取锁，由于锁被线程1占着，所以获取不成功
     * 3.线程2加入到锁链表中【双向链表】，自旋锁修改线程2所在node中的waitStatus=Signal，调用LockSupport.park挂起线程2，等待被唤醒
     * 4.线程1业务处理完毕，释放锁，同时调用LockSupport.unpark唤醒锁链表中head的下一个节点中的线程2
     * 5.线程2获取锁，循环往复1--4
     */

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
