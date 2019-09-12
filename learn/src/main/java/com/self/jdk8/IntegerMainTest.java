package com.self.jdk8;

/**
 * @author shichen
 * @create 2019-08-23
 * @desc
 */
public class IntegerMainTest {

    public static void main(String[] args) {
        Integer a = 40;
        Integer b = 40;
        Integer c = 0;

        Integer d = new Integer(40);
        Integer e = new Integer(40);
        Integer f = new Integer(0);


        //true
        System.out.println(a == b);

        //true
        System.out.println(a == b + c);

        //false
        System.out.println(a == d);

        //false
        System.out.println(d == e);
    }

}
