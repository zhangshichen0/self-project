package com.self.attach;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author shichen
 * @create 2019-09-11
 * @desc
 */
public class AttachMain {

    public static void main(String[] args) throws Exception {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class vmClass = classLoader.loadClass("com.sun.tools.attach.VirtualMachine");
        Object vmObject = null;
        try {
            int pid = ProcessUtils.selectPid();
            vmObject = vmClass.getMethod("attach", String.class).invoke(null, String.valueOf(pid));
            Method loadAgent = vmClass.getMethod("loadAgent", String.class);
            String path = new File(System.getProperty("user.home") + File.separator + "agent-1.0.jar").getAbsolutePath();
            loadAgent.invoke(vmObject, path);
        } finally {
            if (Objects.nonNull(vmObject)) {
                vmClass.getMethod("detach").invoke(vmObject);
                System.out.println("detach success");
            }
        }
    }
}
