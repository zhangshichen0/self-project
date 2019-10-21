package com.self.spring.circularreference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author shichen
 * @create 2019-10-17
 * @desc
 */
@Service
public class A implements AA {;


    /**
     * 用lazy修饰后，在注入属性时，注入的是一个代理对象，并没有真实对象
     */
    @Lazy
    @Autowired
    private BB bb;

    @Async
    @Override
    public void a() {
        System.out.println("A.a");
        System.out.println(bb);
        //用Lazy修饰后，bb在使用时原生对象被初始化
        System.out.println("A" + Thread.currentThread().getName());
        bb.b();

        bb.c();

        System.out.println(bb);
    }

}
