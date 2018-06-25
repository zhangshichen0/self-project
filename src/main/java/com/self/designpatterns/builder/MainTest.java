package com.self.designpatterns.builder;

/**
 * @author shichen
 * @create 2018/6/21
 * @desc
 */
public class MainTest {

    public static void main(String[] args) {
        Configuration configuration = Configuration.newBuilder().uid("1").name("name").build();
        System.out.println(configuration.getName());
    }

}
