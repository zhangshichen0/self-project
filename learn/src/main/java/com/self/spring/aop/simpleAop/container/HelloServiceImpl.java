package com.self.spring.aop.simpleAop.container;

/**
 * @author shichen
 * @create 2018/9/20
 * @desc
 */
@Instance
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
