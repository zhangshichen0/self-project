package com.self.spring.aop.simpleAop.container;

/**
 * @author shichen
 * @create 2018/9/20
 * @desc
 */
public class ContainerMainTest {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.createProxyBeans("com.self.spring.asm.simpleAop.container");

        HelloService helloService = (HelloService) proxyFactory.getBean("com.self.spring.asm.simpleAop.container.HelloService");
        helloService.sayHello("haha");
    }

}
