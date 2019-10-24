package com.self.spring.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author shichen
 * @create 2019-10-24
 * @desc
 */
@EnableAsync
@Configuration
public class AsyncConfiguration implements AsyncConfigurer {

    /**
     * 同时把该线程池声明为bean
     *
     * 如果不使用该方式或者创建TaskExecutor Bean的方式创建异步执行任务线程池的话，则@Async默认使用{@link org.springframework.core.task.SimpleAsyncTaskExecutor}实现
     * @return
     */
    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("MyExecutor-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
