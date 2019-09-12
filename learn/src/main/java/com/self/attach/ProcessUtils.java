package com.self.attach;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author shichen
 * @create 2019-09-12
 * @desc
 */
public class ProcessUtils {

    /**
     * 获取选中的pid
     *
     * @return
     */
    public static int selectPid() {
        Map<Integer, String> processIdsMap = listProcessIdsByJps();

        // print list
        int count = 1;
        for (String process : processIdsMap.values()) {
            if (count == 1) {
                System.out.println("* [" + count + "]: " + process);
            } else {
                System.out.println("  [" + count + "]: " + process);
            }
            count++;
        }

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        System.out.println("line " + line);

        if (line.trim().isEmpty()) {
            // get the first process id
            return processIdsMap.keySet().iterator().next();
        }

        int choice = new Scanner(line).nextInt();

        System.out.println("choice " + choice);

        if (choice <= 0 || choice > processIdsMap.size()) {
            return -1;
        }

        Iterator<Integer> idIter = processIdsMap.keySet().iterator();
        for (int i = 1; i <= choice; ++i) {
            if (i == choice) {
                return idIter.next();
            }
            idIter.next();
        }

        return -1;
    }

    private static Map<Integer, String> listProcessIdsByJps() {
        Map<Integer, String> processIdsMap = new HashMap<>();

        String jps = "jps";
        File jpsFile = findJps();

        if (Objects.nonNull(jps)) {
            jps = jpsFile.getAbsolutePath();
        }

        String[] command = new String[] {jps , "-l"};

        //执行命令
        List<String> lines = ExecutingCommand.runNative(command);
        for (String line : lines) {
            String[] strings = line.trim().split("\\s+");
            if (strings.length < 1) {
                continue;
            }
            int pid = Integer.parseInt(strings[0]);

            //判断pid是否是当前进程pid
            if (pid == PidUtils.getCurrentPid()) {
                continue;
            }

            // skip jps
            if (strings.length >= 2 && isJpsProcess(strings[1])) {
                continue;
            }

            processIdsMap.put(pid, line);
        }

        return processIdsMap;
    }

    /**
     * 获取jps
     *
     * @return
     */
    private static File findJps() {

        String javaHome = System.getProperty("java.home");
        String[] paths = {"bin/jps", "bin/jps.exe", "../bin/jps", "../bin/jps.exe"};

        List<File> jpsList = new ArrayList<File>();
        for (String path : paths) {
            File file = new File(javaHome, path);
            if (file.exists()) {
                jpsList.add(file);
            }
        }

        if (jpsList.isEmpty()) {
            //从system.env中查找
            String javaHomeEnv = System.getenv("JAVA_HOME");
            for (String path : paths) {
                File jpsFile = new File(javaHomeEnv, path);
                if (jpsFile.exists()) {
                    jpsList.add(jpsFile);
                }
            }
        }

        if (jpsList.isEmpty()) {
            return null;
        }

        if (jpsList.size() > 1) {
            jpsList.stream().sorted((o1, o2) -> {
                try {
                    return o2.getCanonicalPath().length() - o1.getCanonicalPath().length();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return -1;
            });
        }

        return jpsList.get(0);
    }

    private static boolean isJpsProcess(String mainClassName) {
        return "sun.tools.jps.Jps".equals(mainClassName) || "jdk.jcmd/sun.tools.jps.Jps".equals(mainClassName);
    }

}
