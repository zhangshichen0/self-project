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
public class Student implements Cloneable {

    private String name;
    private int age;
    private Teacher teacher;


    @Override
    protected Object clone() throws CloneNotSupportedException {
        //直接调用object clone，浅复制
        //return super.clone();

        Student student = (Student) super.clone();
        Teacher teacher = (Teacher) this.teacher.clone();
        student.setTeacher(teacher);
        return student;
    }
}
