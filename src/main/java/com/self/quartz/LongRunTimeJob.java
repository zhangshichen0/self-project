package com.self.quartz;

import java.util.concurrent.TimeUnit;

/**
 * @author shichen
 * @create 2019-04-26
 * @desc
 */
public class LongRunTimeJob implements IJob {
    @Override
    public void process() {

        long start = System.currentTimeMillis();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("LongRunTimeJob 运行完成，耗时" + (System.currentTimeMillis() - start) + "ms");
    }
}
