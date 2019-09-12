package com.self.lock.base_aqs_lock;

import java.util.concurrent.Semaphore;

/**
 * 信号量--可以控制进入执行代码的线程的数量--可以打到限流的作用
 *
 * 例如：
 * 停车场
 * 停车位100 当来一个停车时-1，当停车达到100时，后面来的车需要等待提车场的车开出
 *
 * 车位   信号量
 * 车     线程
 * 停车位 代码
 *
 * @author shichen
 * @create 2018/8/29
 * @desc
 */
public class SemaphoreMainTest {

    public static void main(String[] args) {
        Parking parking = new Parking(10);

        for (int i = 0; i < 20; i ++) {
            Car car = new Car(parking);
            Thread thread = new Thread(car, String.valueOf(i));
            thread.start();
        }
    }

    /**
     * 停车场类
     */
    static class Parking {

        private Semaphore semaphore;

        /**
         * 停车场
         * @param count 车位数
         */
        public Parking(int count) {
            semaphore = new Semaphore(count);
        }

        public void parking() {
            try {
                semaphore.acquire();
                long time = (long) (Math.random() * 10);
                System.out.println(Thread.currentThread().getName() + "进入停车场，停车" + time + "秒..." );
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "开出停车场...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    /**
     * 车
     */
    static class Car implements Runnable {

        private Parking parking;

        public Car(Parking parking) {
            this.parking = parking;
        }

        @Override
        public void run() {
            parking.parking();
        }
    }

}
