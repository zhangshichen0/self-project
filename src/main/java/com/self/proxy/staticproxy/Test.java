package com.self.proxy.staticproxy;

/**
 * @author shichen
 * @create 2018/3/6
 * @desc
 */
public class Test {
    public static void main(String[] args) {
        Zhang zhang = new Zhang();
        People zhangDad = new ZhangDad(zhang);
        zhangDad.zhaoDuiXiang();
    }
}
