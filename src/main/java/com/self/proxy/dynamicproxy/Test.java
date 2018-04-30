package com.self.proxy.dynamicproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shichen
 * @create 2018/3/6
 * @desc
 */
public class Test {

    public static void main(String[] args) throws Throwable {
        People people = new ZhangSan();
        PeopleInvocationHandle peopleInvocationHandle = new PeopleInvocationHandle(people);

        People proxy = (People) Proxy.newProxyInstance(people.getClass().getClassLoader(), new Class[]{People.class}, peopleInvocationHandle);
        System.out.println(proxy);
        proxy.eat();


        People proxy2 = (People) Proxy.newProxyInstance(people.getClass().getClassLoader(), new Class[]{People.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("before");
                method.invoke(new ZhangSan(), args);
                System.out.println("after");
                return null;
            }
        });

        System.out.println(Proxy.isProxyClass(proxy2.getClass()));
        proxy2.sleep();
    }

}
