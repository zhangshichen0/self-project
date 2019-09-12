package com.self.designpatterns.chainofresponsibility.filter;

import com.self.designpatterns.chainofresponsibility.Request;
import com.self.designpatterns.chainofresponsibility.Response;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public abstract class AbstractFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (shouldNotFilter()) {
            filterChain.doFilter(request, response);
        } else {
            doFilterInternal(request, response, filterChain);
        }
    }

    /**
     * 不同的处理
     *
     * @param request
     * @param response
     * @param filterChain
     */
    protected abstract void doFilterInternal(Request request, Response response, FilterChain filterChain);

    /**
     * 不需要过滤
     *
     * @return
     */
    private boolean shouldNotFilter() {
        return false;
    }
}
