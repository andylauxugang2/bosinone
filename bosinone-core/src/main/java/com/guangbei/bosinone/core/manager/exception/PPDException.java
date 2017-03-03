package com.guangbei.bosinone.core.manager.exception;

import com.guangbei.bosinone.core.common.SystemErrorEnum;

/**
 * Created by xugang on 2017/3/2.
 */
public class PPDException extends RuntimeException {

    public PPDException() {
        super();
    }

    public PPDException(SystemErrorEnum errorEnum) {
        super(errorEnum.getErrorMsg());
    }

    public PPDException(String message) {
        super(message);
    }

    public PPDException(String message, Throwable cause) {
        super(message, cause);
    }

    public PPDException(Throwable cause) {
        super(cause);
    }

    protected PPDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
