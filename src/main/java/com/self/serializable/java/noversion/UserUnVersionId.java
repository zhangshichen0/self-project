package com.self.serializable.java.noversion;

import java.io.Serializable;

/**
 * @author shichen
 * @create 2018/5/4
 * @desc
 */
public class UserUnVersionId implements Serializable{
    private Integer id;
    private String name;

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

    @Override
    public String toString() {
        return "UserUnVersionId{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
