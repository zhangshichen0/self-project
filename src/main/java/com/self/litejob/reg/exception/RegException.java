package com.self.litejob.reg.exception;

/**
 * @author shichen
 * @create 2018/6/13
 * @desc
 */
public class RegException extends RuntimeException {

    private static final long serialVersionUID = -7772285043005654640L;

    public RegException(String errorMessage, Object... params) {
        super(String.format(errorMessage, params));
    }

    public RegException(final Exception cause) {
        super(cause);
    }
}
