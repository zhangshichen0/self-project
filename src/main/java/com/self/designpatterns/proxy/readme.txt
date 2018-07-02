代理模式：应用最广泛的一种模式 https://blog.csdn.net/jiankunking/article/details/52143504

作用：
    控制其他对象的方式，相当于在调用对象前，增加一层挡板

分类：
    静态代理
    动态代理

例子：
    spring aop即是动态代理的典型
    proxy文件加下代理
    java.lang.reflect.Proxy
    RMI
    RPC
    等都是代理模式的代表

动态代理实现的两种方式：
    jdk自带：只能对接口进行代理
    cglib：既能对接口又能对实际类进行代理，无法对final修饰的类和方法进行代理，因为final修饰的类和方法不能被继承

代理：
是使用统一的方式对所有要增加同一功能的方法逻辑上增加增强，使用动态代理，会在jvm中动态生成代理类