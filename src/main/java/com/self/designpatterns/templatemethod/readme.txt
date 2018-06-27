模板方法模式

作用：
    定义算法的模板，将具体的算法延迟到子类进行实现，这样在不改变模板的前提下，子类可实现不同的算法逻辑

构成：
    abstractclass: templateMethod, optionalA, optionalB, templateMethod定义调用ab的过程，ab的具体实现在子类中进行


例子：
    冲咖啡和冲茶--》步骤：烧水(boilWater)，泡(brew)，倒入到杯子中(pourInCup)，加入调味品(addCondiments)