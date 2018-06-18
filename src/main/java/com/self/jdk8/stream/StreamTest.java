package com.self.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author shichen
 * @create 2018/6/18
 * @desc
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            list.add(String.valueOf(i));
        }

        StreamTest.reduce(list);

    }

    /**
     * 0#1#2#3#4#5#6#7#8#9
     * reduce操作
     * @param list
     */
    public static void reduce(List<String> list) {
        Optional<String> optionalS = list.stream().reduce(((s, s2) -> s + "#" + s2));
        System.out.println(optionalS.get());
    }

}
