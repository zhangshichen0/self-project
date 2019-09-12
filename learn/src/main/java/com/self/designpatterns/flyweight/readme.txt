享元模式

作用：
    将一些基本不变的且被大量使用的对象进行共享，利用共享的方式来支持大量细粒度的对象，这些对象一部分内部状态是相同的

构成：
    Flyweight：享元对象
    IntrinsicState：内部状态，相同的项元对象共享
    ExtrinsicState：外部状态

例子：
    java.lang.Integer#valueOf(int)
    java.lang.Boolean#valueOf(boolean)
    java.lang.Byte#valueOf(byte)
    java.lang.Character#valueOf(char)
    jvm中堆的字符串常量池