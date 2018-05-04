package com.self.serializable.java.noversion;

import com.self.serializable.java.Operate;

import java.io.IOException;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class TestUnVersionMain {

    public static void main(String[] args) {
        Operate<UserUnVersionId> operate = new Operate<>();

        /*UserUnVersionId user = new UserUnVersionId();
        user.setId(1);
        user.setName("zhangshichen");

        try {
            operate.serializable(user);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //TODO 反序列化,增加属性或者减少属性，异常
        try {
            UserUnVersionId userUnVersionId = operate.deSerializable();
            System.out.println(userUnVersionId);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
