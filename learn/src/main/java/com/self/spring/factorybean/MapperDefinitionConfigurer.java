package com.self.spring.factorybean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
@Component
public class MapperDefinitionConfigurer implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition(UserDao.class).getBeanDefinition();

        String beanClassName = genericBeanDefinition.getBeanClassName();
        genericBeanDefinition.setBeanClass(ProxyBeanFactoryBean.class);
        genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);

        registry.registerBeanDefinition("userDao", genericBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //不做处理
    }
}
