package com.self.threadpool.thread;

/**
 * @author shichen
 * @create 2018/8/14
 * @desc
 */
public class TestThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread interruptThread = new InterruptThread();
        interruptThread.start();

        Thread.sleep(10);

        interruptThread.interrupt();

        Thread.currentThread().interrupt();
        System.out.println("main:" + Thread.currentThread().getName() + "是否停止1？ = " + Thread.interrupted());
        System.out.println("main:" + Thread.currentThread().getName() + "是否停止2？ = " + Thread.interrupted());
        System.out.println(interruptThread.getName() + "是否停止3？ = " + interruptThread.isInterrupted());
        System.out.println(interruptThread.getName() + "是否停止4？ = " + interruptThread.isInterrupted());
    }

}
