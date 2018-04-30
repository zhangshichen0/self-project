package com.self.proxy.dynamicproxy.selfimpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shichen
 * @create 2018/3/6
 * @desc
 */
public class MyInvocationHandlerImpl implements MyInvocationHandler {

    private Object object;

    public MyInvocationHandlerImpl(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
        System.out.println("before");
        method.invoke(object, method, args);
        System.out.println("after");
        return null;
    }
}
