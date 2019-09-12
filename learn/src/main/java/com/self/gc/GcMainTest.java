package com.self.gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 验证  当某个线程出现oom时，并不会影响整个应用的运行，出现oom的线程会被回收
 *
 * 使用g1收集器，查看g1过程
 *
 * @author shichen
 * @create 2019-07-31
 * @desc
 */
public class GcMainTest {

    public static void main(String[] args) throws InterruptedException {


        new Thread(() -> {
            List<byte[]> list = new ArrayList<>();
            for (int i = 0; i < 100; i ++) {
                byte[] bytes = new byte[1024 * 1024];
                list.add(bytes);
            }
        }).start();


        TimeUnit.SECONDS.sleep(10);

        System.out.println(1111);
    }

}
