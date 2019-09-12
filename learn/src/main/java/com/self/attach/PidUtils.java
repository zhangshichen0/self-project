package com.self.attach;

import java.lang.management.ManagementFactory;

/**
 * @author shichen
 * @create 2019-09-12
 * @desc
 */
public class PidUtils {

    private static String CURRENT_PID = "0";

    static {
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("name:" + jvmName);
        int index = jvmName.indexOf('@');

        if (index > 0) {
            CURRENT_PID = jvmName.substring(0, index);
        }
    }

    public static int getCurrentPid() {
        return Integer.parseInt(CURRENT_PID);
    }
}
