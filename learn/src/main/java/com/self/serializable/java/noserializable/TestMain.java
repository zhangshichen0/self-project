package com.self.serializable.java.noserializable;

import com.self.serializable.java.Operate;

import java.io.IOException;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 *
 * TODO 对未实现序列化接口的对象序列化时，异常
 *
 */
public class TestMain {


    public static void main(String[] args) {
        Operate<UserNoSerializable> operate = new Operate<>();

        UserNoSerializable userNoSerializable = new UserNoSerializable();
        userNoSerializable.setId(1);
        userNoSerializable.setName("zhangshichen1");

        try {
            operate.serializable(userNoSerializable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
