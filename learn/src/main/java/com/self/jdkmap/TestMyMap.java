package com.self.jdkmap;

/**
 * @author shichen
 * @create 2018/3/12
 * @desc
 */
public class TestMyMap {
    public static void main(String[] args) {
        MyMap<String, String> myMap = new MyHashMap<>();
        myMap.put("1", "1");

        System.out.println(myMap.get("1"));
    }
}
