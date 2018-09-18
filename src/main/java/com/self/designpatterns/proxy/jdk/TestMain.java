package com.self.designpatterns.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author shichen
 * @create 2018/9/18
 * @desc
 */
public class TestMain {

    public static void main(String[] args) {
        //该设置用于输出jdk动态代理产生的类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloService helloService = new HelloServiceImpl();
        Handler handler = new Handler(helloService);

        HelloService proxy = (HelloService) Proxy.newProxyInstance(TestMain.class.getClassLoader(), new Class[]{HelloService.class, HelloServiceA.class}, handler);
        proxy.say();
    }

}
