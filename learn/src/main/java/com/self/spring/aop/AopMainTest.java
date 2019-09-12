package com.self.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

/**
 * @author shichen
 * @create 2018/8/30
 * @desc
 */
@Aspect
@Component
public class AopMainTest {
    @Pointcut(value = "execution(* com.self..Aop.*())")
    public void myMethod(){}


    @Before("myMethod()")
    public void before() {
        System.out.println("method start");
    }
    @After("myMethod()")
    public void after() {
        System.out.println("method after");
    }
    @AfterReturning("execution(* com.self..Aop.*())")
    public void AfterReturning() {
        System.out.println("method AfterReturning");
    }
    @AfterThrowing("execution(* com.self..Aop.*())")
//  @Around("execution(* com.self..Aop.*())")
    public void AfterThrowing() {
        System.out.println("method AfterThrowing");
    }
    @Around("execution(* com.self..Aop.*())")
    public Object Around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("method Around");
        SourceLocation sl = jp.getSourceLocation();
        Object ret = jp.proceed();
        System.out.println(jp.getTarget());
        return ret;
    }
    @Before("execution(public * com.self..Aop.*()) && args(userId,..)")
    public void before3(int userId) {
        System.out.println("userId-----" + userId);
    }
    @Before("myMethod()")
    public void before2(JoinPoint jp) {
        Object[] args = jp.getArgs();
        System.out.println("userId11111: " + (Integer)args[0]);
        System.out.println(jp.getTarget());
        System.out.println(jp.getThis());
        System.out.println(jp.getSignature());
        System.out.println("method start");
    }
}
