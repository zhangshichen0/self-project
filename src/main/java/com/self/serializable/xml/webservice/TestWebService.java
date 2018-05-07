package com.self.serializable.xml.webservice;


import com.self.serializable.xml.webservice.client.JwsServiceHelloService;

/**
 *
 * https://blog.csdn.net/csdn_gia/article/details/54863549
 *
 * @author shichen
 * @create 2018/5/7
 * @desc
 */
public class TestWebService {
    public static void main(String[] args) {

        JwsServiceHelloService jwsServiceHelloService = new JwsServiceHelloService();
        System.out.println(jwsServiceHelloService.getJwsServiceHelloPort().getValue("ssss"));
    }

}
