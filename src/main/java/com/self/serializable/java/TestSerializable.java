package com.self.serializable.java;

import java.io.*;

/**
 * @author shichen
 * @create 2018/5/3
 * @desc 对实现Serializable接口序列化测试
 */
public class TestSerializable {

    public static void main(String[] args) {
        Operate<ArticleSerializable> operate = new Operate<>();

        //TODO 测试静态变量是否被序列化，把序列化的代码注释掉
        ArticleSerializable articleSerializable = new ArticleSerializable(1, "test", 1);

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
