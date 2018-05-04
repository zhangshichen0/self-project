package com.self.serializable.java.noversion;

import com.self.serializable.java.Operate;

import java.io.IOException;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class TestVersionMain {
    public static void main(String[] args) {
        Operate<UserVersionId> operate = new Operate<>();

        UserVersionId user = new UserVersionId();
        user.setId(1);
        user.setName("zhangshichen");

        try {
            operate.serializable(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO 反序列化,增加属性或者减少属性，反序列化成功
        /*try {
            UserVersionId userVersionId = operate.deSerializable();
            System.out.println(userVersionId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
