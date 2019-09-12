package com.self.designpatterns.facade;

/**
 * @author shichen
 * @create 2018/6/29
 * @desc
 */
public class FacadeMainTest {

    public static void main(String[] args) {

        FacadeSystem facadeSystem = new FacadeSystem("功夫熊猫");
        facadeSystem.watchMovie();
    }

}
