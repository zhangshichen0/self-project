package com.self.serializable.xml.jaxb;

import javax.xml.bind.annotation.*;

/**
 * @author shichen
 * @create 2018/5/3
 * @desc java原生序列化
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Article {

    @XmlElement(name = "Id")
    private Integer id;

    /**
     * 文章标题
     */
    @XmlElement(name = "Title")
    private String title;

    /**
     * TODO 不被序列化 当增加@XmlElement时，无法完成序列化，异常
     */
    private transient Integer age;

    /**
     * TODO 增加注解后，能够写入到生成的xml文件中，但是反序列化后，无法拿到xml文件中的值
     */
    @XmlElement
    public static Integer count = 0;

    public Article() {
    }

    public Article(Integer id, String title, Integer age) {
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
