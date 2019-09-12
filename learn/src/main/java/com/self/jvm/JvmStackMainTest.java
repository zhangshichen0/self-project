package com.self.jvm;

/**
 * 演示java虚拟机栈的操作
 *
 * @author shichen
 * @create 2019-07-29
 * @desc
 */
public class JvmStackMainTest {

    public int add(int m) {
        return m + 1;
    }

    public static void main(String[] args) {
        JvmStackMainTest jvmStackMainTest = new JvmStackMainTest();
        System.out.println(jvmStackMainTest.add(1));
    }

}
