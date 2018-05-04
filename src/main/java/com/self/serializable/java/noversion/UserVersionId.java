package com.self.serializable.java.noversion;

import java.io.Serializable;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class UserVersionId implements Serializable {
    private static final long serialVersionUID = -302566327130117452L;

    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserVersionId{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
