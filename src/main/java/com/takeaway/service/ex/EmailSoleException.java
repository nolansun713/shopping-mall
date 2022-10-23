package com.takeaway.service.ex;

/**
 * @author kafka
 * 邮箱的唯一
 */
public class EmailSoleException extends ServiceException{
    public EmailSoleException() {
        super();
    }

    public EmailSoleException(String message) {
        super(message);
    }

    public EmailSoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSoleException(Throwable cause) {
        super(cause);
    }

    protected EmailSoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
