策略模式

作用：
    定义算法族，能够动态的切换算法

构成：
    Strategy：接口定义了一个算法族，它们都具有 BehaviorInterface() 方法
    Context：是使用到该算法族的类，其中的 doSomething() 方法会调用 BehaviorInterface()，setStrategy(in Strategy)
             方法可以动态地改变 strategy 对象，也就是说能动态地改变 Context 所使用的算法。
