package com.self.serializable.java;

import java.io.Serializable;

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
}
