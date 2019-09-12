package com.self.springmvc.controller;

import com.self.springmvc.model.UserProto;
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
@RequestMapping(value = "/serializable/protobuf")
public class TestProtobufSerializableController {

    /**
     * 返回json数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/x-protobuf")
    public UserProto.UserPackage getUser(int id) {
        UserProto.UserPackage userPackage = UserProto.UserPackage.newBuilder().setId(id).setAge(10).setName("zhangshichen").build();
        return userPackage;
    }

    /**
     * json数据请求
     *
     * http://127.0.0.1:8080/serializable/json/insert
     *
     * @param userPackage
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json")
    public boolean insertUser(@RequestBody UserProto.UserPackage userPackage) {
        return true;
    }

}
