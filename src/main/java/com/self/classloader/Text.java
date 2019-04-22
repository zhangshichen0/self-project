package com.self.classloader;

/**
 *
 * @author shichen
 * @create 2019-04-22
 * @desc
 */
public class Text {

    private static int k1 = 0;
    private static Text t1 = new Text("t1");
    private static Text t2 = new Text("t2");
    private static int i = print("i");
    private static int n = 99;
    private static int j = print("j");

    {
        print("构造快");
    }

    static  {
        print("静态块");
    }


    public Text(String str) {
        System.out.println((++k1) + ":" + str + "   i=" + i + "   n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k1) + ":" + str + "   i=" + i + "   n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String[] args) {
        Text t = new Text("init");
    }
}


