package com.self.spring.listener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;

/**
 * 初始化事件执行器，并指定为异步执行，并在初始化时，指定发生异常后处理情况
 *
 * @author shichen
 * @create 2019-10-21
 * @desc
 */
@Configuration
public class ConfigTaskExecutor implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Autowired
    private TaskExecutor taskExecutor;

    @Bean(name = "applicationEventMulticaster")
    public SimpleApplicationEventMulticaster newSimpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster(this.beanFactory);
        simpleApplicationEventMulticaster.setTaskExecutor(this.taskExecutor);
        simpleApplicationEventMulticaster.setErrorHandler(t -> System.out.println(t));
        return simpleApplicationEventMulticaster;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
