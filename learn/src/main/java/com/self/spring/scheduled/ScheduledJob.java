package com.self.spring.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 定时任务类
 *
 * @author shichen
 * @create 2019-10-23
 * @desc
 */
@Component
public class ScheduledJob {


    @Scheduled(cron = "0/5 * * * * ?")
    public void print1() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " print1, current thread " + Thread.currentThread().getName());
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public static void print2() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " print2, current thread " + Thread.currentThread().getName());
    }

}
