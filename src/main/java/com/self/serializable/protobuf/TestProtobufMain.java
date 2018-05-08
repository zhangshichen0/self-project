package com.self.serializable.protobuf;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author shichen
 * @create 2018/5/8
 * @desc
 */
public class TestProtobufMain {

    public static void main(String[] args) {
        //序列化
        Article.ArticlePackage serializableArticlePackage = Article.ArticlePackage.newBuilder().setId(1).setTitle("test").build();
        System.out.println(serializableArticlePackage.toByteArray().length);

        //反序列化
        try {
            Article.ArticlePackage deSerializableArticlePackage = Article.ArticlePackage.parseFrom(new ByteArrayInputStream(serializableArticlePackage.toByteArray()));
            System.out.println(deSerializableArticlePackage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
