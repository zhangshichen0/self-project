java代理模式讲解

1.什么是代理模式

 静态代理：

 动态代理：目的就是为委托对象提供代理，用以控制外部对委托对象的访问（可以在调用委托对象的前后，加入自己的意图），
          aop(面向切面编程)编程的具体展现

2.代理模式相关的类和接口

 jdk代理：Proxy类生成对接口的代理类，InvocationHandler用来处理代理对象对被代理对象的实际调用


3.代理模式的机制和特点【http://blog.csdn.net/jiankunking/article/details/52143504】

 原理：使用java的反射，反射出对象的各个组成部分（方法，成员变量等）

 代理类的样式：

 ProxyImpl extends Proxy implements interface0, interface1 {

    private InvocationHandler h;

    public ProxyImpl (InvocationHandler h) {
        this.h = h;
    }

    public object method01 (object... args) {
        return this.h.invoke(proxy, method01, args);
    }

    ……
 }

 通过分析代码可以看出Java 动态代理，具体有如下四步骤：
  1.通过实现 InvocationHandler 接口创建自己的调用处理器；
  2.通过为 Proxy 类指定 ClassLoader 对象和一组 interface 来创建动态代理类；
  3.通过反射机制获得动态代理类的构造函数，其唯一参数类型是调用处理器接口类型；
  4.通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数被传入。

4.代理模式的案例+源码分析