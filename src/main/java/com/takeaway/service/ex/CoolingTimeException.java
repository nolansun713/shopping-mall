package com.takeaway.service.ex;

/**
 * 验证码发送时间冷却
 * @author kafka
 */
public class CoolingTimeException extends ServiceException{
    public CoolingTimeException() {
        super();
    }

    public CoolingTimeException(String message) {
        super(message);
    }

    public CoolingTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoolingTimeException(Throwable cause) {
        super(cause);
    }

    protected CoolingTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
