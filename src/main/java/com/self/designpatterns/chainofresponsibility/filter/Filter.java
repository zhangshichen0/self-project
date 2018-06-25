package com.self.designpatterns.chainofresponsibility.filter;

import com.self.designpatterns.chainofresponsibility.Request;
import com.self.designpatterns.chainofresponsibility.Response;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public interface Filter {


    /**
     * 过滤
     *
     * @param request
     * @param filterChain 过滤器链
     * @param response
     */
    void doFilter(Request request, Response response, FilterChain filterChain);

}
