package com.self.spring.di;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shichen
 * @create 2018/9/19
 * @desc
 */
public class DIMainTest {


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> beans = new ConcurrentHashMap<>();

        Class sayServiceClazz = Class.forName("com.self.spring.di.SayService");
        Class helloServiceClazz = Class.forName("com.self.spring.di.HelloService");

        SayService sayService = (SayService) sayServiceClazz.getConstructor().newInstance();
        HelloService helloService = (HelloService) helloServiceClazz.getConstructor().newInstance();

        beans.put(SayService.class.getCanonicalName(), sayService);
        beans.put(HelloService.class.getCanonicalName(), helloService);

        Field[] fields = helloService.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i ++) {
            Field field = fields[i];
            Class clazz = field.getType();
            if (Objects.nonNull(beans.get(clazz.getCanonicalName()))) {
                Method method = helloService.getClass().getMethod(getWriteMethod(field.getName()), SayService.class);
                method.setAccessible(true);
                //注入类型
                method.invoke(helloService, sayService);
            }
        }

        helloService.sayHello();
    }

    private static String getWriteMethod(String fieldName) {
        return "set" + toLowerCaseFirstOne(fieldName);
    }

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
