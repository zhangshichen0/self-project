package com.self.spring.factorybean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
public class MapperDefinitionConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String basePackage;

    private String suffix;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathScanner classPathScanner = new ClassPathScanner(registry, false, suffix);
        classPathScanner.registerFilters();

        //扫描指定包路径，从而生成beanDefinition
        classPathScanner.scan(StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //不做处理
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
