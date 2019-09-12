package com.self.classloader;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型测试，字节码执行流程
 *
 *
 * @author shichen
 * @create 2019-07-19
 * @desc
 */
public class GenericMainTest {

    /**
     * Code:
     *       stack=5, locals=4, args_size=1
     *          0: new           #2                  // class java/util/ArrayList
     *          3: dup
     *          4: invokespecial #3                  // Method java/util/ArrayList."<init>":()V
     *          7: astore_1
     *          8: aload_1
     *          9: new           #4                  // class com/self/classloader/GenericMainTest$User
     *         12: dup
     *         13: iconst_1
     *         14: ldc           #5                  // String 1
     *         16: invokespecial #6                  // Method com/self/classloader/GenericMainTest$User."<init>":(ILjava/lang/String;)V
     *         19: invokeinterface #7,  2            // InterfaceMethod java/util/List.add:(Ljava/lang/Object;)Z
     *         24: pop
     *         25: aload_1
     *         26: new           #4                  // class com/self/classloader/GenericMainTest$User
     *         29: dup
     *         30: iconst_2
     *         31: ldc           #8                  // String 2
     *         33: invokespecial #6                  // Method com/self/classloader/GenericMainTest$User."<init>":(ILjava/lang/String;)V
     *         36: invokeinterface #7,  2            // InterfaceMethod java/util/List.add:(Ljava/lang/Object;)Z
     *         41: pop
     *         42: iconst_0
     *         43: istore_2
     *         44: iload_2
     *         45: aload_1
     *         46: invokeinterface #9,  1            // InterfaceMethod java/util/List.size:()I
     *         51: if_icmpge     78      比较false的话，跳转到78行
     *         54: aload_1
     *         55: iload_2
     *         56: invokeinterface #10,  2           // InterfaceMethod java/util/List.get:(I)Ljava/lang/Object;
     *         61: checkcast     #4                  // class com/self/classloader/GenericMainTest$User
     *         64: astore_3
     *         65: getstatic     #11                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *         68: aload_3
     *         69: invokevirtual #12                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
     *         72: iinc          2, 1   局部变量表中第二个变量+1，并设置回局部变量表
     *         75: goto          44  跳转到第44行
     *         78: return
     *       LineNumberTable:
     *         line 16: 0
     *         line 17: 8
     *         line 18: 25
     *         line 20: 42
     *         line 21: 54
     *         line 22: 65
     *         line 20: 72
     *         line 24: 78
     *       StackMapTable: number_of_entries = 2
     *         frame_type = 253  append
     *          offset_delta =44
     *  locals =[

    class java/util/List,int ]
            *frame_type =250  chop
            *offset_delta =33
     * @param args
     */
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "1"));
        userList.add(new User(2, "2"));

        try {
            //成功放上，说明编译后进行了类型擦除
            userList.getClass().getMethod("add", Object.class).invoke(userList, "xxxxx");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < userList.size(); i++) {
            //使用反射添加的元素，在这里通过checkcast检查失败
            User user = userList.get(i);
            System.out.println(user);
        }
    }


    static class User {
        private int age;
        private String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
