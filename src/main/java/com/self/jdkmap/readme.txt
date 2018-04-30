HashMap原理与分析:http://blog.csdn.net/a_long_/article/details/51594159

1.equals(Object c)和hashCode()方法分析【集合中存储的对象需要这两个方法】

 1.重写equals(Object c)需要满足一下条件
  自反性：对于任何非空引用值 x，x.equals(x) 都应返回 true。
  对称性：对于任何非空引用值 x 和 y，当且仅当 y.equals(x) 返回 true 时，x.equals(y) 才应返回 true。
  传递性：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。
  一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改。

 2.重写对象的equals方法，一般都需要重写hashCode方法，满足集合的要求

 3.两个对象hashCode的值相等，不一定对象相等，但是两个对象相等，则hashCode一定相等

2.分析

 1.存储结构

  1.底层使用的是数组+链表+红黑树（1.8加入）结构，使用key的hashCode+equals计算index【见图hashMapPut.jpeg】

 2.

3.扩容

 1.当创建HashMap指定容量大小时，如果放入的元素<=cap,则不进行扩容

 2.扩容时，必须是2的n次幂的原因：使用位运算快速的计算出元素的index，2n次幂能够快速计算出二进制  1n个0 -1 => 0n个1
  static int indexFor(int h, int length) {
      return h & (length-1);
  }
  假设length = 16, h = 20, 二进制 10000  length - 1 => 01111  h => 10100  h & (length - 1) = 00100 => 4 及index=4

  h:      10   01010               01010              01010

  length: 16   10000   length - 1  01111      比如：   01000  低位全部为1

  存储位置:                         01010              01000

  2n次幂好处：
        1.散列的位置被低位限制，数据散列的位置，永远被限制在索引范围之内
        2.如果length-1的二进制低位0越多，那么得到的散列的位置越固定，导致冲突越多，数组利用率越低


4.多个线程同时操作一个HashMap对象会出现什么问题