package com.self.mybatis;

import java.lang.reflect.Method;

/**
 * @author shichen
 * @create 2018/9/13
 * @desc
 */
public class MapperMethod {

    private Method method;
    private Class<?> mapperInterface;

    public MapperMethod(Method method, Class<?> mapperInterface) {
        this.method = method;
        this.mapperInterface = mapperInterface;
    }

    public Object execute() {
        Object result;
        System.out.println("executor " + mapperInterface.getName() + "." + this.method.getName() + " success");
        result = "success";
        return result;
    }

}
