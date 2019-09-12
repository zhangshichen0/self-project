package com.self.designpatterns.chainofresponsibility.common;

import com.self.designpatterns.chainofresponsibility.Request;
import com.self.designpatterns.chainofresponsibility.Response;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public class Type3Handler extends Handler {

    public Type3Handler(Handler handler) {
        super(handler);
    }

    /**
     * 具体处理逻辑
     *
     * @param request
     */
    @Override
    protected Response process(Request request) {
        if (request.getType() == Request.RequestType.type3) {
            return new Response("response2", Response.ResponseType.response2);
        }
        return null;
    }
}
