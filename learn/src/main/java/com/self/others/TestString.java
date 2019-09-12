package com.self.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author shichen
 * @create 2018/3/8
 * @desc
 */
public class TestString {

    /*public static void main(String[] args) {
        String a = "a";
        String b = new String("a");
        String c = "a";
        String d = new String("a");
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(Objects.equals(a, c));
        System.out.println(b == c);
        System.out.println(b.equals(c));

        System.out.println(d.equals(b));


        String[] str = new String[2];
        System.out.println(str.length);

        List<String> stringList = new ArrayList<>(10);
        System.out.println(stringList.size());
    }
*/

    public static String appendStr(String s) {
        s = s + "bbb";
        return s;
    }

    public static StringBuilder appendSb(StringBuilder s) {
        return s.append("bbbb");
    }

    public static void main(String[] args) {
        String s = new String("aa");
        String ns = TestString.appendStr(s);

        System.out.println("appendStr---->" + s);
        System.out.println("appendStr--ns--->" + ns);
    }

}
