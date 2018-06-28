适配器模式：https://blog.csdn.net/u010288264/article/details/53835185

作用：
    把一个类接口转换成另一个用户需要的接口，Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以在一起工作

解决的问题：
    在不使用适配器模式的情况下，可能需要大量的if else去判断，调用不同接口

构成：
    adapter:适配器接口
    adaptee:受改造者


例子：
    springMVC的HandlerAdapter，用来适配不同的Controller


使用案例：
    springMVC的HandlerAdapter，用来适配不同的Controller
    java.util.Arrays#asList()
    java.util.Collections#list()