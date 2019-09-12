package com.self.spring.aop.simpleAop.container;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shichen
 * @create 2018/9/19
 * @desc
 */
public class JdkDynamicAopProxy implements InvocationHandler {

    private Object target;

    public JdkDynamicAopProxy(Object target) {
        this.target = target;
    }

    /**
     * 创建代理对象
     *
     * @param interfaces
     * @return
     */
    public Object newInstance(Class<?>[] interfaces) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("start");
        method.invoke(this.target, args);
        System.out.println("end");

        return null;
    }
}
