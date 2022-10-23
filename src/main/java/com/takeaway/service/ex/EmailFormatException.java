package com.takeaway.service.ex;

/**
 * @author kafka
 * 邮箱验证码格式问题
 */
public class EmailFormatException extends ServiceException{
    public EmailFormatException() {
        super();
    }

    public EmailFormatException(String message) {
        super(message);
    }

    public EmailFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailFormatException(Throwable cause) {
        super(cause);
    }

    protected EmailFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
