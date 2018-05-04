java序列化有两种方式：
1.实现Serializable
2.实现Externalizable


注：
1.在类中显示声明serialVersionUID时，当类中成员变化时，仍旧能够反序列化成功
2.在类中不显示声明serialVersionUID时，根据java序列化规范，会根据成员生成唯一id，当类中成员增加或者减少时，反序列化不成功