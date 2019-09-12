package com.self.designpatterns.nullobject;

/**
 * @author shichen
 * @create 2018/6/27
 * @desc
 */
public class NullObjectMainTest {

    public static void main(String[] args) {

        //避免空指针异常
        AbstractOperation operation = func(-1);
        operation.request();

    }

    public static AbstractOperation func(int para) {
        if (para < 0) {
            //当输入异常时，返回空对象，避免调用该对象方法时出现空指针异常
            return new NullOperation();
        }
        return new RealOperation();
    }
}
