package com.self.spring.factorybean;


import org.springframework.beans.factory.FactoryBean;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
public class ProxyBeanFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;


    public ProxyBeanFactoryBean() {
    }

    public ProxyBeanFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        return ProxyFactory.getProxy(this.mapperInterface);
    }

    @Override
    public Class<T> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
