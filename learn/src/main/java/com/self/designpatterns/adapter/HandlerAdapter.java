package com.self.designpatterns.adapter;

/**
 * @author shichen
 * @create 2018/6/28
 * @desc
 */
public interface HandlerAdapter {

    /**
     * 是否支持适配该对象
     *
     * @param handler
     * @return
     */
    boolean support(Object handler);

    /**
     * 处理
     *
     * @param handler
     */
    String handle(Object handler);
}
