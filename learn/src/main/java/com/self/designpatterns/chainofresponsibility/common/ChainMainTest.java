package com.self.designpatterns.chainofresponsibility.common;

import com.self.designpatterns.chainofresponsibility.Request;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public class ChainMainTest {

    public static void main(String[] args) {
        Handler handler1 = new Type1Handler(null);
        Handler handler2 = new Type2Handler(handler1);
        Handler handler3 = new Type3Handler(handler2);

        Request request = new Request(Request.RequestType.type1, "type1");
        handler3.handler(request);
    }

}
