package com.self.designpatterns.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shichen
 * @create 2018/6/28
 * @desc
 */
public class AdapterMainTest {

    private List<HandlerAdapter> adapterList = new ArrayList<>();

    public static void main(String[] args) {
        AdapterMainTest adapterMainTest = new AdapterMainTest();
        adapterMainTest.initAdapter();

        Controller controller = new ServletForwardingController();
        HttpRequestHandler httpRequestHandler = new DefaultServletHttpRequestHandler();

        HandlerAdapter ha = null;
        for (int i = 0; i < adapterMainTest.adapterList.size(); i ++) {
            if (adapterMainTest.adapterList.get(i).support(controller)) {
                ha = adapterMainTest.adapterList.get(i);
                break;
            }
        }

        ha.handle(controller);
    }

    private void initAdapter() {
        HandlerAdapter requestHandlerAdapter = new RequestMappingHandlerAdapter();
        HandlerAdapter simpleControllerAdapter = new SimpleControllerHandlerAdapter();

        this.adapterList.add(requestHandlerAdapter);
        this.adapterList.add(simpleControllerAdapter);
    }
}
