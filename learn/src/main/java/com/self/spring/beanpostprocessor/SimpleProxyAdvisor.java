package com.self.spring.beanpostprocessor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author shichen
 * @create 2019-10-21
 * @desc
 */
public class SimpleProxyAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {
    private Advice advice;

    private Pointcut pointcut;


    /**
     * Create a new {@code AsyncAnnotationAdvisor} for the given task executor.
     * (can be {@code null} to trigger default executor resolution)
     * handle unexpected exception thrown by asynchronous method executions
     */
    @SuppressWarnings("unchecked")
    public SimpleProxyAdvisor() {
        this.advice = buildAdvice();
        this.pointcut = buildPointcut();
    }


    /**
     * Set the {@code BeanFactory} to be used when looking up executors by qualifier.
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware) this.advice).setBeanFactory(beanFactory);
        }
    }


    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }


    protected Advice buildAdvice() {
        return new SimpleProxyInterceptor();
    }

    /**
     * Calculate a pointcut for the given async annotation types, if any.
     *
     * @return the applicable Pointcut object, or {@code null} if none
     */
    protected Pointcut buildPointcut() {
        return Pointcut.TRUE;
    }
}
