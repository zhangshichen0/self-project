package com.self.threadbreak;

import java.util.HashMap;

/**
 * @author shichen
 * @create 2018/10/26
 * @desc
 */
public class ThreadBreakMainTest {
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2, 0.75f);

    public static void main(String[] args) {

        new Thread("Thread1-Name") {
            @Override
            public void run() {
                System.out.println("Thread1-Name Start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //断点位置 1
                map.put(7, 77);
                System.out.println(map);
            }
        }.start();


        new Thread("Thread2-Name") {

            @Override
            public void run() {
                try {
                    System.out.println("Thread2-Name Start");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 断点位置2
                map.put(3, 33);
                System.out.println(map);
            }

        }.start();


        // 断点位置 3
        System.out.println("Thread-Main-Name Start");
        System.out.println("Thread-Main-Name Start");
        System.out.println("Thread-Main-Name Start");


        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
