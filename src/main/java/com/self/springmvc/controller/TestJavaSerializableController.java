package com.self.springmvc.controller;

import com.self.springmvc.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shichen
 * @create 2018/5/9
 * @desc
 */
@RestController
@RequestMapping(value = "/serializable/java", produces = "application/x-java-serialization")
public class TestJavaSerializableController {

    @RequestMapping(value = "/get")
    public User getUser(int id) {
        User returnUser = new User();
        returnUser.setId(id);
        returnUser.setAge(10);
        returnUser.setName("zhangshichen");
        return returnUser;
    }

}
