package com.self.designpatterns.adapter;

/**
 * @author shichen
 * @create 2018/6/28
 * @desc
 */
public class ServletForwardingController implements Controller {
    /**
     * 处理
     *
     * @return
     */
    @Override
    public void handleRequest() {
        System.out.println("i am ServletForwardingController");
    }
}
