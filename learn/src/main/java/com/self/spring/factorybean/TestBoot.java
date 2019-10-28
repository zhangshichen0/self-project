package com.self.spring.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
public class TestBoot {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.scan("com.self.spring.factorybean");
        ac.refresh();

        UserDao userDao = (UserDao)ac.getBean("userDao");
        userDao.query();
    }

}
