package com.self.serializable.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shichen
 * @create 2018/5/3
 * @desc
 */
public class Operate<T> {

    /**
     * 序列化方法
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void serializable(T t) throws IOException {
        this.serializable(t, false);
    }

    /**
     *
     * @param t
     * @param cover 是否覆盖已存在的文件
     */
    public void serializable(T t, boolean cover) throws IOException {
        FileOutputStream fileOutputStream = null;
        File file = new File("a.txt");
        if (file.exists() && cover) {
            file.delete();
        }
        fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(t);
        outputStream.close();
        fileOutputStream.close();
    }

    /**
     * 反序列化的方法
     *
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public T deSerializable() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("a.txt");
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        T t = (T) ois.readObject();
        ois.close();
        fileInputStream.close();
        return t;
    }
}
