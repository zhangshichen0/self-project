package com.self.designpatterns.proxy.jdk;

/**
 * @author shichen
 * @create 2018/9/18
 * @desc
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public void say() {
        System.out.println("hello");
    }
}
