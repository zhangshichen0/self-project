package com.self.serializable.java;

import java.io.IOException;

/**
 * @author shichen
 * @create 2018/5/3
 * @desc
 */
public class TestExternalizable {

    public static void main(String[] args) {
        ArticleExternalizable articleExternalizable = new ArticleExternalizable();
        articleExternalizable.setId(2);
        articleExternalizable.setTitle("test2");
        articleExternalizable.setAge(2);

        Operate<ArticleExternalizable> operate = new Operate<>();
        try {
            operate.serializable(articleExternalizable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ArticleExternalizable _articleExternalizable = operate.deSerializable();
            System.out.println(_articleExternalizable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
