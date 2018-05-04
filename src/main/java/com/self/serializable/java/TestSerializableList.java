package com.self.serializable.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shichen
 * @create 2018/5/3
 * @desc 演示类序列化一遍后，如果遇到相同的类的对象，只记录一次对象类信息
 */
public class TestSerializableList {

    public static void main(String[] args) {
        ArticleSerializable articleSerializable1 = new ArticleSerializable(1, "1", 1);
        ArticleSerializable articleSerializable2 = new ArticleSerializable(2, "2", 2);
        ArticleSerializable articleSerializable3 = new ArticleSerializable(3, "3", 3);

        List<ArticleSerializable> list = new ArrayList<>();
        list.add(articleSerializable1);
        list.add(articleSerializable2);
        list.add(articleSerializable3);

        Operate<ArticleSerializable> operate = new Operate<>();
        try {
            operate.serializable(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
