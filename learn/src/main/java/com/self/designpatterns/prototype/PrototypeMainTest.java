package com.self.designpatterns.prototype;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public class PrototypeMainTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher teacher = new Teacher("zhangsan", 30);

        Student student = new Student("liming", 10, teacher);


        Student student1 = (Student) student.clone();
        student1.getTeacher().setName("zhangshichen");

        System.out.println("student" + student.toString());
        System.out.println("student1" + student1);

    }

}
