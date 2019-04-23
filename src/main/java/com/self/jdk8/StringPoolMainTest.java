package com.self.jdk8;

/**
 * 字符串常量池在jdk8中默认存在于堆中
 *
 * @author shichen
 * @create 2019-04-23
 * @desc
 */
public class StringPoolMainTest {

    public static void main(String[] args) {
        while (true) {
            String a = new String("我查的大幅度大幅度发大发送到发而发的发生大嘎达发").intern();
        }
    }

}
