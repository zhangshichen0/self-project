package com.self.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author shichen
 * @create 2019-10-22
 * @desc
 */
@Component
@Aspect
public class AfterReturnAspect {

    @Pointcut("execution(* com.self.spring..*.*(..))")
    public void pointCut() {

    }

    @Before(value = "pointCut()")
    public void before(JoinPoint jp) {
        System.out.println("执行方法前，before被调用");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnVal")
    public void afterReturning(JoinPoint jp, Object returnVal) {
        System.out.println("执行方法后，afterReturning被调用,return " + returnVal);
    }

    public void avoid() {
        System.out.println("avoid");
    }

}
