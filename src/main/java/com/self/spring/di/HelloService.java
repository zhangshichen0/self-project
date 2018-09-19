package com.self.spring.di;

/**
 * @author shichen
 * @create 2018/9/19
 * @desc
 */
public class HelloService {

    private SayService sayService;

    public void sayHello() {
        System.out.println("say");
        sayService.hello();
    }

    public void setSayService(SayService sayService) {
        this.sayService = sayService;
    }
}
