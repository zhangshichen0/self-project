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
public class FilterMainTest {

    public static void main(String[] args) {
        List<Filter> filters = new ArrayList<>();
        filters.add(new Request1Filter());
        filters.add(new Request2Filter());
        filters.add(new Request3Filter());

        ComposeFilter composeFilter = new ComposeFilter();
        composeFilter.setFilters(filters);

        composeFilter.doFilter(new Request(Request.RequestType.type2, "type2"), new Response(), new DefaultFilterChain());
    }

}
