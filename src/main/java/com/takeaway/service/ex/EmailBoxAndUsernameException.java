package com.takeaway.service.ex;

/**
 * 邮箱和用户名不一致
 * @author kafka
 */
public class EmailBoxAndUsernameException extends ServiceException{
    public EmailBoxAndUsernameException() {
        super();
    }

    public EmailBoxAndUsernameException(String message) {
        super(message);
    }

    public EmailBoxAndUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailBoxAndUsernameException(Throwable cause) {
        super(cause);
    }

    protected EmailBoxAndUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
