package com.self.proxy.dynamicproxy.selfimpl;

import com.self.proxy.dynamicproxy.People;
import com.self.proxy.dynamicproxy.ZhangSan;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.nio.charset.Charset;

/**
 * @author shichen
 * @create 2018/3/6
 * @desc
 */
public class MyProxy {

    private MyInvocationHandler h;


    private static String rt = "\r\r";


    protected MyProxy (MyInvocationHandler invocationHandler) {
        this.h = invocationHandler;
    }

    /**
     * 生成代理类对象
     *
     * @param classLoader
     * @param inf
     * @param invocationHandler
     * @return
     */
    public static Object newProxyInstance(ClassLoader classLoader, Class inf, MyInvocationHandler invocationHandler) {
        //构建代理类
        Method[] methods = inf.getMethods();

        String proxyClassStr = "package com.self.proxy.dynamicproxy.selfimpl;" + rt
                + "import " + inf.getName() + ";" + rt
                + "import java.lang.reflect.Method;" + rt
                + "public class $Proxy0 implements " + inf.getSimpleName() + " {" + rt
                + "private MyInvocationHandler h;" + rt
                + "public $Proxy0(MyInvocationHandler h) {" + rt
                + "this.h = h;}" + rt + getMethodsStr(methods, inf) + rt
                + "}";

        System.out.println(proxyClassStr);

        String path = "/Users/shichen/program/worker/self-project/src/main/java/com/self/proxy/dynamicproxy/selfimpl/$Proxy0.java";
        File file = new File(path);

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(proxyClassStr);
            fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, Charset.defaultCharset());
        Iterable iterable = standardJavaFileManager.getJavaFileObjects(file);

        JavaCompiler.CompilationTask compilationTask = javaCompiler.getTask(null, standardJavaFileManager, null, null, null, iterable);
        compilationTask.call();
        try {
            standardJavaFileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            MyClassLoader myClassLoader = new MyClassLoader("file:/Users/shichen/program/worker/self-project/src/main/java/com/self/proxy/dynamicproxy/selfimpl/", classLoader);
            Class clazz = myClassLoader.findClass("$Proxy0");
            Constructor constructor = clazz.getConstructor(MyInvocationHandler.class);
            return constructor.newInstance(invocationHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成代理类方法
     * @param methods
     * @param inf
     * @return
     */
    private static String getMethodsStr(Method[] methods, Class inf) {
        String methodsStr = "";
        for (int i = 0; i < methods.length; i ++) {
            Method method = methods[i];
            methodsStr += "@Override" + rt
                    + "public void " + method.getName() + " () throws Throwable{" + rt
                    + "Method method = " + inf.getName() + ".class.getMethod(\""+ method.getName() + "\", new Class[]{});" + rt
                    + "this.h.invoke(this, method, new Object[]{});" + rt
                    + "}" + rt;
        }
        return methodsStr;
    }

    public static void main(String[] args) throws Throwable {
        MyInvocationHandler myInvocationHandler = new MyInvocationHandlerImpl(new ZhangSan());
        People people = (People) MyProxy.newProxyInstance(MyProxy.class.getClassLoader(), People.class, myInvocationHandler);
        people.eat();
        System.out.println(15>>2);
    }
}
