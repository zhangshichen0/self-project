package com.self.proxy.dynamicproxy.selfimpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shichen
 * @create 2018/3/6
 * @desc
 */
public interface MyInvocationHandler {

    /**
     * 处理方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object invoke(Object proxy, Method method, Object... args) throws Throwable;

}
