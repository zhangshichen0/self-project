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
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("a.txt"));
        outputStream.writeObject(t);
        outputStream.close();
    }

    /**
     * 反序列化的方法
     *
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public T deSerializable() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("a.txt"));
        T t = (T) ois.readObject();
        ois.close();
        return t;
    }
}
