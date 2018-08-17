package com.self.lock.sync_lock;

/**
 * 验证synchronized的wait逻辑
 *
 * @author shichen
 * @create 2018/6/10
 * @desc
 */
public class TestObjectWait {

    private Object lock = new Object();

    public void testWait() {
        long before = System.currentTimeMillis();
        synchronized (lock) {
            System.out.println("获得锁，并等待" + Thread.currentThread().getId());
            try {
                //从功能上来说wait就是说线程在获取对象锁后，主动释放对象锁，同时本线程休眠，加入到等待线程队列中
                lock.wait(10000);
                //sleep并不释放对象锁
                Thread.sleep(10000);
                System.out.println("等待1s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("执行完成" + Thread.currentThread().getId());
    }

    public static void main(String[] args) throws InterruptedException {
        TestObjectWait testWait = new TestObjectWait();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                testWait.testWait();
            }
        });

        Thread.sleep(1000);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testWait.testWait();
            }
        });

        thread.start();
        thread1.start();

    }

}
