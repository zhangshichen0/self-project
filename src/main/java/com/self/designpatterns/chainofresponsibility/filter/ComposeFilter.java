package com.self.designpatterns.chainofresponsibility.filter;

import com.self.designpatterns.chainofresponsibility.Request;
import com.self.designpatterns.chainofresponsibility.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
public class ComposeFilter implements Filter {

    private List<? extends Filter> filters = new ArrayList<>();

    /**
     * 过滤器链
     *
     * @param filters
     */
    public void setFilters(List<? extends Filter> filters) {
        this.filters = filters;
    }

    /**
     * 过滤
     *
     * @param request
     * @param response
     * @param filterChain 过滤器链
     */
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        new VirtualFilterChain(filterChain, filters).doFilter(request, response);
    }

    private static class VirtualFilterChain implements FilterChain {

        private FilterChain originalChain;

        /**
         * 增加的filter
         */
        private List<? extends Filter> additionalFilters;

        private int currentPosition = 0;

        /**
         * 构建虚拟filter链
         *
         * @param originalChain
         * @param additionalFilters
         */
        public VirtualFilterChain(FilterChain originalChain, List<? extends Filter> additionalFilters) {
            this.originalChain = originalChain;
            this.additionalFilters = additionalFilters;
        }

        /**
         * 过滤器链
         *
         * @param request
         * @param response
         */
        @Override
        public void doFilter(Request request, Response response) {
            if (currentPosition == additionalFilters.size()) {
                this.originalChain.doFilter(request, response);
                System.out.println(response);
            } else {
                currentPosition ++;
                Filter nextFilter = additionalFilters.get(currentPosition - 1);
                nextFilter.doFilter(request, response, this);
            }
        }
    }
}
