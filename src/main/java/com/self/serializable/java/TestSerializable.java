package com.self.serializable.java;

import java.io.*;

/**
 * @author shichen
 * @create 2018/5/3
 * @desc 对实现Serializable接口序列化测试
 */
public class TestSerializable {

    public static void main(String[] args) {
        ArticleSerializable articleSerializable = new ArticleSerializable(1, "test", 1);

        Operate<ArticleSerializable> operate = new Operate<>();
        try {
            operate.serializable(articleSerializable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArticleSerializable _articleSerializable = operate.deSerializable();
            System.out.println(_articleSerializable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
