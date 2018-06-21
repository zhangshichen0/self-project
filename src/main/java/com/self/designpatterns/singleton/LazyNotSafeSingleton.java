package com.self.designpatterns.singleton;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class LazyNotSafeSingleton {

    private static AtomicInteger count = new AtomicInteger();

    /**
     * 私有静态变量
     */
    private static LazyNotSafeSingleton instance;

    /**
     * 私有构造函数
     */
    private LazyNotSafeSingleton(){
        try {
            //模拟实例创建流程
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 公有静态方法
     *
     * @return
     */
    public static LazyNotSafeSingleton getInstance() {
        if (Objects.isNull(instance)) {
            instance = new LazyNotSafeSingleton();
            System.out.println("初始化次数：" + count.incrementAndGet());
        } else {
            System.out.println("实例已经被初始化");
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            LazyNotSafeSingleton lazyNotSafeSingleton = getInstance();
        });
        Thread thread2 = new Thread(() -> {
            LazyNotSafeSingleton lazyNotSafeSingleton = getInstance();
        });
        Thread thread3 = new Thread(() -> {
            LazyNotSafeSingleton lazyNotSafeSingleton = getInstance();
        });
        Thread thread4 = new Thread(() -> {
            LazyNotSafeSingleton lazyNotSafeSingleton = getInstance();
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
