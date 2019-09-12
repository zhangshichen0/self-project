package com.self.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

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
        StreamTest.parallelStream(list);


        //生成1--9 * 2 的列表
        int[] a = range(1, 10).map(i -> i * 2).toArray();
        List<Integer> result = range(1, 10).map(i -> i * 2).boxed().collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 0#1#2#3#4#5#6#7#8#9
     * reduce操作
     * @param list
     */
    private static void reduce(List<String> list) {
        Optional<String> optionalS = list.stream().reduce(((s, s2) -> s + "#" + s2));
        System.out.println(optionalS.get());
    }

    /**
     * 并行处理流【不一定快】
     *
     * @param list
     */
    private static void parallelStream(List<String> list) {
        long start = System.currentTimeMillis();
        list.parallelStream().filter(s -> Integer.parseInt(s) % 2 == 0).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println((System.currentTimeMillis() - start));
    }

}
