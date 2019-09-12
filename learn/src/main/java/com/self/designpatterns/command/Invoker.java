package com.self.designpatterns.command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shichen
 * @create 2018/6/26
 * @desc
 */
public class Invoker {

    private static final Map<Integer, Command> COMMAND_MAP = new HashMap<>();

    /**
     * 注册命令
     *
     * @param code
     * @param command
     */
    public void registerCommand(Integer code, Command command) {
        COMMAND_MAP.put(code, command);
    }

    /**
     * 执行命令
     * @param code
     */
    public void execute(Integer code) {
        COMMAND_MAP.get(code).execute();
    }

}
