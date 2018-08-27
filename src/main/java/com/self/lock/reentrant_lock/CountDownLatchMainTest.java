package com.self.lock.reentrant_lock;

import java.util.concurrent.CountDownLatch;

/**
 * boss等待员工进行开会
 *
 * @author shichen
 * @create 2018/8/27
 * @desc
 */
public class CountDownLatchMainTest {

    private static CountDownLatch bossWait = new CountDownLatch(5);

    /**
     * boss线程
     *
     */
    static class BossThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("boss 等待");
                //进入锁队列
                bossWait.await();
                System.out.println("boss 开会");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 员工线程
     */
    static class EmployeeThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到达会议室……");
            //每进来一个人 就释放一次，等到释放到=0时，唤醒等待的boss线程
            bossWait.countDown();
        }
    }

    public static void main(String[] args) {

        new Thread(new BossThread()).start();

        for(int i = 0; i < 5; i ++) {
            new Thread(new EmployeeThread(), String.valueOf(i + 1)).start();
        }

    }

}
