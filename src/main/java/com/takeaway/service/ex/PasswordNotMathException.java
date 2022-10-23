package com.takeaway.service.ex;

/**
 * 密码错误异常
 * @author kafka
 */
public class PasswordNotMathException extends ServiceException{
    public PasswordNotMathException() {
        super();
    }

    public PasswordNotMathException(String message) {
        super(message);
    }

    public PasswordNotMathException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMathException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotMathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
