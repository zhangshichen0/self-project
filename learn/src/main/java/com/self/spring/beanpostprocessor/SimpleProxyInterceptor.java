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
        Object result = invocation.proceed();

        System.out.println("执行结果" + result);

        return result;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
