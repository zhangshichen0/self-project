package com.self.designpatterns.chainofresponsibility.filter;

import com.self.designpatterns.chainofresponsibility.Request;
import com.self.designpatterns.chainofresponsibility.Response;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public class Request1Filter extends AbstractFilter {
    /**
     * 不同的处理
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(Request request, Response response, FilterChain filterChain) {
        if (request.getType() == Request.RequestType.type1) {
            response.setName("response1");
            response.setResponseType(Response.ResponseType.response1);
        }
        filterChain.doFilter(request, response);
    }
}
