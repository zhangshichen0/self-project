package com.self.spring.factorybean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
public class ProxyFactory {

    public static <T> T getProxy(Class mapperInterface) {
        return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{mapperInterface}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("执行代理bean");

                return null;
            }
        });
    }

}
