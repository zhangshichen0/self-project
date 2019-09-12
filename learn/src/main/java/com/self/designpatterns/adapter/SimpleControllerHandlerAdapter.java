package com.self.designpatterns.adapter;

/**
 * @author shichen
 * @create 2018/6/28
 * @desc
 */
public class SimpleControllerHandlerAdapter implements HandlerAdapter {
    /**
     * 是否支持适配该对象
     *
     * @param handler
     * @return
     */
    @Override
    public boolean support(Object handler) {
        if (handler instanceof Controller) {
            return true;
        }
        return false;
    }

    /**
     * 处理
     *
     * @param handler
     */
    @Override
    public String handle(Object handler) {
        ((Controller)handler).handleRequest();
        return "null";
    }
}
