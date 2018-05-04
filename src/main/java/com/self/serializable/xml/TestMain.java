package com.self.serializable.xml;

import com.thoughtworks.xstream.XStream;

import java.io.*;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class TestMain {

    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1);
        article.setTitle("test");
        article.setAge(10);
        Article.count = 20;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("xmlT.xml");
            OutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            XStream xStream = new XStream();
            xStream.toXML(article, outputStream);

            String xml = xStream.toXML(article);
            System.out.println("序列化后对象大小：" + xml.getBytes("UTF-8").length);

            outputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //反序列化
        try {
            Article.count = 30;
            FileInputStream fileInputStream = new FileInputStream("xmlT.xml");
            InputStream inputStream = new ObjectInputStream(fileInputStream);
            XStream xStream = new XStream();
            Article article1 = (Article) xStream.fromXML(inputStream);
            System.out.println(article1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
