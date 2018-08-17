package com.self.lock.reentrant_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shichen
 * @create 2018/8/17
 * @desc
 */
public class ConditionMainTest {

    /**
     * condition执行步骤说明
     *
     *  1. 线程1调用lock，获取锁
     *  2. 线程1调用await，将自己加入到condition链表中，等待signal信号，并释放锁，唤醒等待锁的线程2
     *  3. 线程2获得锁，调用signal，将线程1节点从condition链表中移动到aqs锁链表中【此时线程2未释放锁，线程1为被唤醒】
     *  4. 线程2调用unlock释放锁，唤醒aqs链表中的线程1节点
     *  5. 线程1再次获得锁，并继续从await代码后执行，直到执行完成并释放锁
     *
     */

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public void lock1() {
        lock.lock();
        try {
            System.out.println("线程：" + Thread.currentThread().getName() + "获得锁");
            Thread.sleep(10000);
            //加入到condition链表并释放锁
            condition.await();
            System.out.println("线程：" + Thread.currentThread().getName() + "被唤醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public void lock2() {
        lock.lock();
        try {
            //将在condition链表中第一个node移到锁链表中，但不唤醒
            System.out.println("线程：" + Thread.currentThread().getName() + "获得锁");
            condition.signal();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        ConditionMainTest conditionMainTest = new ConditionMainTest();
        Thread thread1 = new Thread(() -> {
            conditionMainTest.lock1();
        }, "1");

        Thread thread2 = new Thread(() -> {
            conditionMainTest.lock2();
        }, "2");

        thread1.start();

        //主线程休眠1s中，确保线程1先获得锁
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }

}
