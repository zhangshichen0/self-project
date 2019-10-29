package com.self.spring.factorybean;

import com.self.spring.factorybean.dao.UserDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
public class TestBoot {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        UserDao userDao = ac.getBean(UserDao.class);
        userDao.query();
    }

}
