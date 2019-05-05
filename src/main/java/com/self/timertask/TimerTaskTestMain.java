package com.self.timertask;

import java.util.Timer;

/**
 * @author shichen
 * @create 2019-05-05
 * @desc
 */
public class TimerTaskTestMain {

    public static void main(String[] args) {
        Timer timer = new Timer();
        //缺点：timer当发生异常时 退出任务
        timer.schedule(new Job(), 2000, 2000);
    }

}
