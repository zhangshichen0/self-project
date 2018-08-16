package com.self.lock.sync_lock;

/**
 * @author shichen
 * @create 2018/8/16
 * @desc
 */
public class ClassAndObjectLockMainTest {

    private final Object lock = new Object();

    /**
     * 类锁
     */
    public static void classLock() {
        synchronized (ClassAndObjectLockMainTest.class) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void objectLock() {
        synchronized (lock) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁");
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
