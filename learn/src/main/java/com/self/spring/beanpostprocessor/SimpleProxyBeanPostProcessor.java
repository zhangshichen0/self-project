package com.self.spring.beanpostprocessor;

import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author shichen
 * @create 2019-10-21
 * @desc
 */
@Component
public class SimpleProxyBeanPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor {

    public SimpleProxyBeanPostProcessor() {
        setBeforeExistingAdvisors(true);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {

        super.setBeanFactory(beanFactory);

        SimpleProxyAdvisor advisor = new SimpleProxyAdvisor();

        advisor.setBeanFactory(beanFactory);
        this.advisor = advisor;
    }
}
