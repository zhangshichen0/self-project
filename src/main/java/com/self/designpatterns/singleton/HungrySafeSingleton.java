package com.self.designpatterns.singleton;

/**
 * 饿汉式--线程安全
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class HungrySafeSingleton {

    /**
     *  类加载时，即被创建
     */
    private static HungrySafeSingleton instance = new HungrySafeSingleton();
    
    private HungrySafeSingleton() {
        try {
            //模拟实例创建流程
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("实例被初始化");
    }

    /**
     * 直接返回实例
     * @return
     */
    public static HungrySafeSingleton getInstance() {
        return instance;
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            HungrySafeSingleton hungrySafeSingleton = getInstance();
        });
        Thread thread2 = new Thread(() -> {
            HungrySafeSingleton hungrySafeSingleton = getInstance();
        });
        Thread thread3 = new Thread(() -> {
            HungrySafeSingleton hungrySafeSingleton = getInstance();
        });
        Thread thread4 = new Thread(() -> {
            HungrySafeSingleton hungrySafeSingleton = getInstance();
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
