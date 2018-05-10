package com.self.serializable.protobuf;

import java.io.*;

/**
 * @author shichen
 * @create 2018/5/8
 * @desc
 */
public class TestProtobufMain {

    public static void main(String[] args) {
        //序列化
        Article.ArticlePackage serializableArticlePackage = Article.ArticlePackage.newBuilder().setId(1).setTitle("test").build();
        System.out.println(serializableArticlePackage.getSerializedSize());

        try {
            //将序列化后的内容存放到文件
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("protobuf.proto"));
            objectOutputStream.write(serializableArticlePackage.toByteArray());
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //反序列化
        try {
            Article.ArticlePackage deSerializableArticlePackage = Article.ArticlePackage.parseFrom(new ByteArrayInputStream(serializableArticlePackage.toByteArray()));
            System.out.println(deSerializableArticlePackage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
