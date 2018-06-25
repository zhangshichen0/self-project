package com.self.designpatterns.chainofresponsibility;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
@AllArgsConstructor
@Getter
public class Request {

    private RequestType type;
    private String name;

    public enum RequestType {
        type1, type2, type3, type4
    }

}
