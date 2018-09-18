package com.self.arthas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shichen
 * @create 2018/9/18
 * @desc
 */
public class Demo {

    static class Counter {
        private static AtomicInteger count = new AtomicInteger(0);
        public static void increment() {
            count.incrementAndGet();
        }
        public static int value() {
            return count.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            Counter.increment();
            System.out.println("counter: " + Counter.value());
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
