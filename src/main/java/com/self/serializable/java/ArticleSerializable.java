package com.self.serializable.java;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shichen
 * @create 2018/5/3
 * @desc java原生序列化
 */
public class ArticleSerializable implements Serializable {

    private static final long serialVersionUID = -6895710234585335090L;

    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * TODO 使用transient修饰的字段也不被序列化
     */
    private transient Integer age;

    /**
     * 静态变量不被序列化
     */
    private static Integer count = 0;


    public ArticleSerializable(Integer id, String title, Integer age) {
        this.id = id;
        this.title = title;
        this.age = age;
        count ++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id:" + id + ", title:" + title + ", age:" + age + ", count:" + count;
    }

    /**
     * TODO 自定义序列化过程，可以使用默认的，在ObjectOutPutStream第1496行，利用反射调用对象里的重写方法
     * @param oos
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(new Date());
    }

    /**
     * TODO 自定义反序列化过程，可以使用默认的，在ObjectInPutStream第1909行，利用反射调用类中重写的方法
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = (Date)ois.readObject();
        System.out.println(simpleDateFormat.format(date));
    }
}
