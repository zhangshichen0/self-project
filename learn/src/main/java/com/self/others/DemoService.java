package com.self.others;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shichen
 * @create 2018-12-21
 * @desc
 */
public class DemoService {

    /**
     * 对象创建时初始化 对象消亡也消亡
     */
    private ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * 随着类初始化而创建，是类成员变量，之初始化一次
     */
    private static DemoModel demoModel = new DemoModel();

    public DemoModel getDemo() {
        System.out.println("xxx:" + demoModel);
        DemoModel demoModel1 = demoModel;
        return demoModel1;
    }

    public void lock() {
        reentrantLock.lock();
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + ",获得锁");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("lock:" + Thread.currentThread().getName() + " " + reentrantLock);
            System.out.println("当前线程：" + Thread.currentThread().getName() + ",休眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("当前线程：" + Thread.currentThread().getName() + ",释放锁");
            reentrantLock.unlock();
        }
    }
}
