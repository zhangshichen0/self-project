package com.self.spring.beanpostprocessor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.Ordered;

/**
 * @author shichen
 * @create 2019-10-21
 * @desc
 */
public class SimpleProxyInterceptor implements MethodInterceptor, Ordered {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("执行SimpleProxy");
        long startTime = System.currentTimeMillis();
        Object result = invocation.proceed();
        System.out.println("方法" + invocation.getMethod().getName() + "，执行结果" + result + ",耗费时间：" + (System.currentTimeMillis() - startTime) + "ms");
        return result;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
