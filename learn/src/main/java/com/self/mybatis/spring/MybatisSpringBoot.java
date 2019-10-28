package com.self.mybatis.spring;

import com.self.mybatis.spring.service.PlayService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
public class MybatisSpringBoot {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        PlayService playService = annotationConfigApplicationContext.getBean(PlayService.class);
        playService.listPlay();

    }

}
