package com.self.designpatterns.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author shichen
 * @create 2018/9/18
 * @desc
 */
public class Handler implements InvocationHandler {

    private Object target;

    public Handler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("start------");
        method.invoke(target, args);
        System.out.println("end------");
        return null;
    }
}
