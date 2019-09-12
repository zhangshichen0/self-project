package com.self.lock.sync_lock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shichen
 * @create 2018/8/16
 * @desc
 */
public class ClassAndObjectLockMainTest {


    /**
     * 对象锁和类锁
     * 两个互不影响的锁，类锁是对访问类方法访问互斥，对象锁是对引用同一对象加锁的对象方法互斥
     */

    private final Object lock = new Object();

    /**
     * 类锁
     */
    public static void classLock() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        synchronized (ClassAndObjectLockMainTest.class) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁,获得锁时间:" + sdf.format(new Date()));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void objectLock() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        synchronized (lock) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁,获得锁时间:" + sdf.format(new Date()));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ClassAndObjectLockMainTest classAndObjectLockMainTest = new ClassAndObjectLockMainTest();

        /**
         * 类锁
         */
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ClassAndObjectLockMainTest.classLock();
            }
        }, "1");

        /**
         * 对象锁
         */
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                classAndObjectLockMainTest.objectLock();
            }
        }, "2");

        thread1.start();
        thread2.start();

    }

}
