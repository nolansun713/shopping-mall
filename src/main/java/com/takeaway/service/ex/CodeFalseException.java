package com.takeaway.service.ex;

/**
 * @author kafka
 * 验证码错误
 */
public class CodeFalseException extends ServiceException{
    public CodeFalseException() {
        super();
    }

    public CodeFalseException(String message) {
        super(message);
    }

    public CodeFalseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeFalseException(Throwable cause) {
        super(cause);
    }

    protected CodeFalseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
