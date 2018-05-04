package com.self.serializable.xml;

import com.thoughtworks.xstream.XStream;

import java.io.*;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class Operate<T> {

    /**
     * 序列化
     * @param t
     */
    public void serializable(T t) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("xmlT.xml");
            OutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            XStream xStream = new XStream();
            xStream.toXML(t, outputStream);

            String xml = xStream.toXML(t);
            System.out.println("object:" + t + ",序列化后对象大小：" + xml.getBytes("UTF-8").length);

            outputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     * @return 返回对象
     */
    public T deSerializable() {
        try {
            Article.count = 30;
            FileInputStream fileInputStream = new FileInputStream("xmlT.xml");
            InputStream inputStream = new ObjectInputStream(fileInputStream);
            XStream xStream = new XStream();
            T t = (T) xStream.fromXML(inputStream);
            System.out.println(t);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
