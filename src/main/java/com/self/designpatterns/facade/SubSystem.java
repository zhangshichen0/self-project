package com.self.designpatterns.facade;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public class SubSystem {

    public void turnOnTV() {
        System.out.println("turnOnTV");
    }

    public void setCd(String cd) {
        System.out.println("cd " + cd);
    }

    public void watchingTv() {
        System.out.println("watchingTv");
    }

}
