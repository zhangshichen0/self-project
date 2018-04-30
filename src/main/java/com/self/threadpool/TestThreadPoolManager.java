package com.self.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author shichen
 * @create 2018/3/2
 * @desc
 */
public class TestThreadPoolManager {

    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPoolManager.getThreadPoolManager(5, new LinkedBlockingQueue<>(100));

        List<Runnable> carList = new ArrayList<>();
        for (int i = 0; i < 500; i ++) {
            //carList.add(new Car(i));
            threadPool.execute(new Car(i + 1));
        }

        //threadPool.execute(carList);

        threadPool.destroy();
     }

    static class Car implements Runnable {

        private int catId = 0;

        public Car (int catId) {
            this.catId = catId;
        }

        @Override
        public void run() {
            System.out.println("任务：" + catId + "被执行,处理的线程为：" + Thread.currentThread().getName());
        }
    }
}
