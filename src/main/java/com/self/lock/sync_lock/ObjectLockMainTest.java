package com.self.lock.sync_lock;

/**
 * @author shichen
 * @create 2018/8/16
 * @desc
 */
public class ObjectLockMainTest {

    private final Object lock = new Object();
    public void lock1() {
        synchronized (lock) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁");
            try {

                synchronized (lock) {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁");
                }
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程：" + Thread.currentThread().getName() + "执行完成");
        }
    }

    public void lock2() {
        synchronized (lock) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程：" + Thread.currentThread().getName() + "执行完成");

        }

    }

    /**
     * 对应本类实例对象监视器
     */
    public synchronized void lock3() {
        System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程：" + Thread.currentThread().getName() + "执行完成");

    }

    /**
     * 对应本类实例对象监视器
     */
    public synchronized void lock4() {
        System.out.println("当前线程：" + Thread.currentThread().getName() + "获得锁");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程：" + Thread.currentThread().getName() + "执行完成");

    }

    public static void main(String[] args) {

        ObjectLockMainTest objectLockMainTest = new ObjectLockMainTest();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLockMainTest.lock1();
            }
        }, "1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLockMainTest.lock2();
            }
        }, "2");

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLockMainTest.lock3();
            }
        }, "3");

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLockMainTest.lock4();
            }
        }, "4");

        thread1.start();
        //thread2.start();
        thread3.start();
        //thread4.start();
    }

}
