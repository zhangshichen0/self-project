package com.self.litejob.reg.exception;

import org.apache.zookeeper.KeeperException;

/**
 * @author shichen
 * @create 2018/6/13
 * @desc
 */
public final class RegExceptionHandler {

    /**
     * 处理异常
     * @param cause
     */
    public static void handleException(Exception cause) {
        if(null == cause) {
            return;
        }
        //验证是否是需要跳过的异常
        if (isIgnoredException(cause) || null != cause.getCause() && isIgnoredException(cause.getCause())) {
            return;
        }

        throw new RegException(cause);
    }

    private static boolean isIgnoredException(Throwable cause) {
        if (cause instanceof KeeperException.ConnectionLossException || cause instanceof KeeperException.NoNodeException
                || cause instanceof KeeperException.NodeExistsException) {
            return true;
        }
        return false;
    }
}
