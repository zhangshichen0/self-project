package com.self.spring;

import com.self.spring.circularreference.AA;
import com.self.spring.circularreference.BB;
import com.self.spring.circularreference.CC;
import com.self.spring.listener.HelloEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shichen
 * @create 2019-10-17
 * @desc
 */
public class SpringTestMain {

    public static void main(String[] args) {
        //该设置用于输出jdk动态代理产生的类
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-main.xml");
        AA a = (AA)applicationContext.getBean("a");
        System.out.println(a.getClass());
        a.a();

        BB b = (BB)applicationContext.getBean("b");
        System.out.println(b.getClass());


        CC c = (CC)applicationContext.getBean("c");
        System.out.println(c.getClass());
        c.cc();


        //发布事件
        applicationContext.publishEvent(new HelloEvent(new Object()));

    }

}
