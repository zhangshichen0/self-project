package com.self.designpatterns.chainofresponsibility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author shichen
 * @create 2018/6/25
 * @desc
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {

    private String name;
    private ResponseType responseType;


    public enum ResponseType {
        response1, response2, response3, response4
    }

}
