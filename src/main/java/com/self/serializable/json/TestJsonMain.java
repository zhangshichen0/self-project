package com.self.serializable.json;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class TestJsonMain {

    public static void main(String[] args) {
        Article article = new Article();
        article.setId(1);
        article.setTitle("test");
        article.setAge(10);
        Article.count = 20;

        String json = JSONObject.toJSONString(article);
        try {
            //序列化内容到文件
            FileOutputStream fileOutputStream = new FileOutputStream("fastjson.json");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(json);

            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("object:" + article + ",序列化后文件大小：" + json.getBytes("UTF-8").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //反序列化
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("fastjson.json");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            String deJson = (String) objectInputStream.readObject();
            Article _article = JSONObject.parseObject(deJson, Article.class);
            System.out.println("反序列化后对象：" + _article + ",从文件中读取的内容：" + deJson);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
