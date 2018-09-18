package com.self.designpatterns.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author shichen
 * @create 2018/9/18
 * @desc
 */
public class ArountAdvice implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("start");
        methodProxy.invokeSuper(o, objects);
        System.out.println("end");
        return null;
    }
}
