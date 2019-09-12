package com.self.designpatterns.chainofresponsibility.common;

import com.self.designpatterns.chainofresponsibility.Request;
import com.self.designpatterns.chainofresponsibility.Response;

import java.util.Objects;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public abstract class Handler {

    private Handler successorHandler;

    public Handler(Handler handler){
        this.successorHandler = handler;
    }

    /**
     * 处理入口类
     * @param request
     */
    public void handler(Request request) {
        Response response = process(request);
        if (Objects.nonNull(response)) {
            System.out.println("response:" + response.toString());
            return;
        } else {
            if (Objects.nonNull(successorHandler)) {
                this.successorHandler.handler(request);
            } else {
                System.out.println("not have fix handler for this request");
            }
        }
    }

    /**
     * 具体处理逻辑
     */
    protected abstract Response process(Request request);
}
