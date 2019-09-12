package com.self.timertask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

/**
 * @author shichen
 * @create 2019-05-05
 * @desc
 */
public class Job extends TimerTask {

    @Override
    public void run() {
        System.out.println("当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
