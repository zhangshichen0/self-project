package com.self.spring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * helloEvent事件监听器
 *
 * @author shichen
 * @create 2019-10-21
 * @desc
 */
@Component
public class HelloEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if (event instanceof HelloEvent) {
            //自己做逻辑处理
            System.out.println("触发HelloEvent事件" + Thread.currentThread().getName());
        }
    }
}
