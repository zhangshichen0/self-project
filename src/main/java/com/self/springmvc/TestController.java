package com.self.springmvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shichen
 * @create 2018/3/5
 * @desc
 */
@RestController
@RequestMapping("/self/test")
public class TestController {

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("hello");
        return "Hello";
    }

}
