package com.self.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author shichen
 * @create 2018/3/6
 * @desc
 */
public class PeopleInvocationHandle implements InvocationHandler {

    private Object object;

    public PeopleInvocationHandle(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("调用方法前处理：" + method.getName());

        Object returnValue = method.invoke(object, args);

        System.out.println("调用方法后处理：" + method.getName());
        return returnValue;
    }
}
