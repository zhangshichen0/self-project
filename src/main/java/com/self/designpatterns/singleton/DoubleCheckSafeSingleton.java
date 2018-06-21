package com.self.designpatterns.singleton;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 双重校验锁方式
 *
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class DoubleCheckSafeSingleton {

    /**
     * 增加volatile,是为了当该对象变化时，其他线程可见
     */
    private static volatile DoubleCheckSafeSingleton instance;
    private static AtomicInteger count = new AtomicInteger();

    private DoubleCheckSafeSingleton() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("实例被初始化:" + count.incrementAndGet());
    }

    /**
     * 获取实例
     * @return
     */
    public static DoubleCheckSafeSingleton getInstance() {
        //第一次验证
        if (Objects.isNull(instance)) {
            //静态方法时类属性，所以使用类锁
            synchronized (DoubleCheckSafeSingleton.class) {
                //第二次验证
                if (Objects.isNull(instance)) {
                    instance = new DoubleCheckSafeSingleton();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            DoubleCheckSafeSingleton doubleCheckSafeSingleton = getInstance();
        });
        Thread thread2 = new Thread(() -> {
            DoubleCheckSafeSingleton doubleCheckSafeSingleton = getInstance();
        });
        Thread thread3 = new Thread(() -> {
            DoubleCheckSafeSingleton doubleCheckSafeSingleton = getInstance();
        });
        Thread thread4 = new Thread(() -> {
            DoubleCheckSafeSingleton doubleCheckSafeSingleton = getInstance();
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }
}
