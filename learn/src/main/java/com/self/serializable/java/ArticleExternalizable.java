package com.self.serializable.java;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shichen
 * @create 2018/5/3
 * @desc
 */
public class ArticleExternalizable implements Externalizable {

    private static final long serialVersionUID = -3540011563962630327L;
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * TODO 由于age没有在writeExternal方法中输出流，所以没有被序列化
     */
    private Integer age;

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
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(title);
        //将日期输出到流中了，所以反序列化能拿到
        Date date = new Date();
        out.writeObject(date);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (Integer) in.readObject();
        title = (String) in.readObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date) in.readObject();
        System.out.println("反序列化后的日期为:" + sdf.format(date));
    }

    @Override
    public String toString() {
        return "id:" + id + ", title:" + title + ", age:" + age;
    }
}
