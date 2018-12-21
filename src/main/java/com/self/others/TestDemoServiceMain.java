package com.self.others;


/**
 * 测试类的成员变量初始化后，每个对象对应的成员变量是否为同一个
 *
 * 结果： 不是同一个
 *
 * @author shichen
 * @create 2018-12-21
 * @desc
 */
public class TestDemoServiceMain {

    public static void main(String[] args) {
        DemoService demoService1 = new DemoService();
        DemoModel xx1 = demoService1.getDemo();
        System.out.println(xx1);

        DemoService demoService2 = new DemoService();
        DemoModel xx2 = demoService2.getDemo();
        System.out.println(xx2);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                DemoService demoService = new DemoService();
                demoService.lock();
            }
        }, "thread-1");


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                DemoService demoService = new DemoService();
                demoService.lock();
            }
        }, "thread-2");


        thread1.start();
        thread2.start();
    }

}
