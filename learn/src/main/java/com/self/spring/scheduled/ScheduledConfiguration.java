package com.self.spring.scheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 初始化执行定时任务的线程池，如果不进行设置，默认线程池的大小为1，也就是当一个任务执行时，其他任务将被阻塞
 * @Schedule 注解被@{@link org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor}处理
 *
 * @author shichen
 * @create 2019-10-23
 * @desc
 */
@Configuration
@EnableScheduling
public class ScheduledConfiguration implements SchedulingConfigurer {

    /**
     * 定义执行任务的线程池
     *
     * @return
     */
    @Bean
    public Executor taskScheduler() {
        return new ScheduledThreadPoolExecutor(2, new ThreadFactory() {
            AtomicInteger id = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "Scheduled-Thread-" + id.incrementAndGet());
                thread.setDaemon(false);
                return thread;
            }
        });
    }

    /**
     * 初始化任务
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
    }
}
