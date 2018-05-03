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
        FileOutputStream fileOutputStream = new FileOutputStream("a.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(t);
        outputStream.close();
        fileOutputStream.close();
    }

    /**
     * 序列化方法
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void serializable(List<T> list) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("b.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        for (T t : list) {
            outputStream.writeObject(t);
        }
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
