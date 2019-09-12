package com.self.springmvc.controller;

import com.self.springmvc.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shichen
 * @create 2018/5/9
 * @desc
 */
@RestController
@RequestMapping(value = "/serializable/json", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class TestJsonSerializableController {

    /**
     * 返回json数据
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User getUser(User user) {
        User returnUser = new User();
        returnUser.setId(user.getId());
        returnUser.setAge(10);
        returnUser.setName("zhangshichen");
        return returnUser;
    }

    /**
     * json数据请求
     *
     * http://127.0.0.1:8080/serializable/json/insert
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public boolean insertUser(@RequestBody User user) {
        return true;
    }

}
