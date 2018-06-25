package com.self.designpatterns.chainofresponsibility.filter;

import com.self.designpatterns.chainofresponsibility.Request;
import com.self.designpatterns.chainofresponsibility.Response;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public class DefaultFilterChain implements FilterChain {
    /**
     * 过滤器链
     *
     * @param request
     * @param response
     */
    @Override
    public void doFilter(Request request, Response response) {
        System.out.println("nothing");
    }
}
