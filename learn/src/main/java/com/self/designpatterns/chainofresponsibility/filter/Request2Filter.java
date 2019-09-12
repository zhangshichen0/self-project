package com.self.designpatterns.chainofresponsibility.filter;

import com.self.designpatterns.chainofresponsibility.Request;
import com.self.designpatterns.chainofresponsibility.Response;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public class Request2Filter extends AbstractFilter {
    /**
     * 不同的处理
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(Request request, Response response, FilterChain filterChain) {
        if (request.getType() == Request.RequestType.type2) {
            response.setName("response2");
            response.setResponseType(Response.ResponseType.response2);
        }
        filterChain.doFilter(request, response);
    }
}
