package com.self.designpatterns.adapter;

/**
 * @author shichen
 * @create 2018/6/28
 * @desc
 */
public class DefaultServletHttpRequestHandler implements HttpRequestHandler{
    /**
     * 处理
     *
     * @return
     */
    @Override
    public String handleRequest() {
        System.out.println("i am DefaultServletHttpRequestHandler");
        return "DefaultServletHttpRequestHandler";
    }
}
