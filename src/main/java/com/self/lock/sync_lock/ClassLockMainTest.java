package com.self.lock.sync_lock;

/**
 * @author shichen
 * @create 2018/8/16
 * @desc
 */
public class ClassLockMainTest {

    /**
     * 类锁
     *
     * 类方法存在类锁竞争
     */

    public static void lock1() {
        synchronized (ClassLockMainTest.class) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void lock2() {
        synchronized (ClassLockMainTest.class) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ClassLockMainTest.lock1();
            }
        }, "1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ClassLockMainTest.lock2();
            }
        }, "2");

        thread1.start();
        thread2.start();

    }

}
