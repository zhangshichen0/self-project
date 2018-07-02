package com.self.designpatterns.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shichen
 * @create 2018/7/2
 * @desc
 */
public class FlyweightFactory {

    private final static Map<String, Flyweight> map = new HashMap<>();

    public static Flyweight getFlyweight(String intrinsicState) {
        Flyweight flyweight = map.get(intrinsicState);
        if (null == flyweight) {
            flyweight = new ConcreteFlyweight(intrinsicState);
            map.put(intrinsicState, flyweight);
        }
        return flyweight;
    }

}
