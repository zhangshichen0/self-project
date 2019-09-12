package com.self.designpatterns.proxy.jdk;

/**
 * @author shichen
 * @create 2018/9/18
 * @desc
 */
public class HelloServiceImpl implements HelloService, HelloServiceA{
    @Override
    public void say() {
        System.out.println("hello");
        //这儿走的是真正实例的方法，而不是代理对象的方法
        this.hello();
    }

    @Override
    public void hello() {
        System.out.println("heheheheheh");
    }

    @Override
    public void sayA() {
        System.out.println("helloa");
    }
}
