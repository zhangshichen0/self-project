package com.self.designpatterns.chainofresponsibility.common;

import com.self.designpatterns.chainofresponsibility.Request;
import com.self.designpatterns.chainofresponsibility.Response;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public class Type1Handler extends Handler {

    public Type1Handler(Handler handler) {
        super(handler);
    }

    /**
     * 处理
     */
    @Override
    protected Response process(Request request) {
        if (request.getType() == Request.RequestType.type1) {
            return new Response("response1", Response.ResponseType.response1);
        }
        return null;
    }
}
