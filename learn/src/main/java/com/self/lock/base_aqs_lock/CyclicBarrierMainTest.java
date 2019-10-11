package com.self.lock.base_aqs_lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 回环栅栏 可以重复利用CyclicBarrier
 *
 * @author shichen
 * @create 2019-10-11
 * @desc
 */
public class CyclicBarrierMainTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("最后一个线程运行完，触发所有等待线程"));

        for (int i = 0; i < 2; i ++) {
            Thread thread = new Thread(() -> {
                try {
                    int index = cyclicBarrier.await();
                    System.out.println("线程名称:" + Thread.currentThread().getName() + "运行完成" + " index:" + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("线程名称:" + Thread.currentThread().getName() + "运行完成");
            }, "" + i);
            thread.start();
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println("主线程退出");
    }

}
