package com.self.serializable.java.noversion;

import com.self.serializable.java.Operate;

import java.io.IOException;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class TestMain {

    public static void main(String[] args) {
        Operate<User> operate = new Operate<>();

        /*User user = new User();
        user.setId(1);
        user.setName("zhangshichen");

        try {
            operate.serializable(user);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //TODO 反序列化,增加属性或者减少属性，异常
        try {
            User user = operate.deSerializable();
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
