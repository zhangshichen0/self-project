package com.self.designpatterns.proxy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author shichen
 * @create 2018/9/18
 * @desc
 */
public class CglibMainTest {

    public static void main(String[] args) {
        //指定代理类文件存放目录
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "clazz");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Test.class);
        enhancer.setCallback(new ArountAdvice());
        Test test = (Test)enhancer.create();
        test.say();
    }

}
