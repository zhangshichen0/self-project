空对象模式：https://www.cnblogs.com/haodawang/articles/5962531.html

作用：
    使用什么都不做的空对象来替代 NULL。
    一个方法返回 NULL，意味着方法的调用端需要去检查返回值是否是 NULL，这么做会导致非常多的冗余的检查代码。
    并且如果某一个调用端忘记了做这个检查返回值，而直接使用返回的对象，那么就有可能抛出空指针异常。

构成：
    AbstractOperation：定义操作
    RealOperation：有操作的
    NullOperation：无操作的对象


注：
    jdk和google guava都提供了Optional，达到对null对象封装的目的，可以使用Optional代替空对象模式