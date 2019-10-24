package com.self.spring;

import com.self.spring.circularreference.AA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 使用注解的方式代替使用xml方式启动spring容器
 *
 * @author shichen
 * @create 2019-10-24
 * @desc
 */
@Configuration
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
public class SpringAnnotationBoot {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.self.spring");
        applicationContext.refresh();

        AA a = (AA)applicationContext.getBean("a");
        System.out.println(a.getClass());
        a.a();
    }

}
