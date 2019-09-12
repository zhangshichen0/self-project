package com.self.jvm;

import java.util.ArrayList;

/**
 * 字符串常量池测试位置，抛出java heap space oom异常
 *
 * @author shichen
 * @create 2019-07-29
 * @desc
 */
public class StringConstantStoreAreaMainTest {

    public static void main(String[] args) throws InterruptedException {

        //会被放入字符串常量池中
        String str = "abc";
        char[] arrays = {'a', 'b', 'c'};
        //重新创建了一个对象
        String str2 = new String(arrays);
        //返回字符串常量池中的引用
        str2 = str2.intern();
        System.out.println(str == str2);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            for (int j = 0; j < 1000000; j++) {
                list.add(String.valueOf(i + j /1000000).intern());
            }
        }
    }

}
