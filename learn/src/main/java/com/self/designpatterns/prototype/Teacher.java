package com.self.designpatterns.prototype;

import lombok.*;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teacher implements Cloneable{

    private String name;
    private int age;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
