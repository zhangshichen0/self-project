package com.self.others;

import java.util.concurrent.TimeUnit;

/**
 * 测试StringBuffer的线程安全性--单独调用一个方法是线程安全的，
 * 但是线程间不断调用append则输出的字符串不定
 *
 * @author shichen
 * @create 2018-12-25
 * @desc
 */
public class TestStringBuffer {

    private static StringBuffer SB = new StringBuffer();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i ++) {
                SB.append("1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i ++) {
                SB.append("2");
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(SB.toString());
    }
}
