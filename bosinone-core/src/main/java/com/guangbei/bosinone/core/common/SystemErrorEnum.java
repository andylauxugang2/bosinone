package com.guangbei.bosinone.core.common;

import com.guangbei.bosinone.client.result.Result;

/**
 * Created by xugang on 2016/11/1.
 */
public enum SystemErrorEnum {

    SYSTEM_ERROR("SYSTEM001", "非常抱歉，系统异常，请您稍后再试"),
    PARAM_IS_NOT_VALIDATE_ERROR("SYSTEM002", "您输入的参数有误,请重新输入"),
    PARAM_IS_NOT_VALIDATE_FORMAT_ERROR("SYSTEM003", "您输入的参数[%s]有误,请重新输入");

    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    SystemErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isEqual(Result rs) {
        return this.getErrorCode().equals(rs.getErrorCode());
    }

    public void fillResult(Result rs) {
        rs.setErrorCode(getErrorCode());
        rs.setErrorMsg(getErrorMsg());
    }

}
