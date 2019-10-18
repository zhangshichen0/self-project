package com.self.spring.circularreference;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shichen
 * @create 2019-10-17
 * @desc
 */
public class SpringTestMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-main.xml");
        AA a = (AA)applicationContext.getBean("a");
        System.out.println(a.getClass());
        a.a();

        BB b = (BB)applicationContext.getBean("b");
        System.out.println(b.getClass());

    }

}
