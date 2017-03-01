package com.guangbei.bosinone.api.domain.result;

import com.guangbei.bosinone.api.common.CodeEnum;

import java.io.Serializable;

/**
 * Created by xugang on 2017/3/1.
 */
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 9019756582385584992L;

    private String version = "1.0";

    /**
     * 错误码
     */
    private int status;

    /**
     * 错误描述信息
     */
    private String message;

    /**
     * vo 对象
     */
    public T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static <T> ApiResult<T> createResult(CodeEnum codeEnum) {

        return createResult(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static <T> ApiResult<T> createResult(CodeEnum codeEnum, String message) {

        return createResult(codeEnum.getCode(), message);
    }

    public static <T> ApiResult<T> createResult(int status, String message) {

        ApiResult<T> result = new ApiResult<T>();
        result.setData(null);
        result.setStatus(status);
        result.setMessage(message);

        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setData(data);
        result.setStatus(CodeEnum.SUCCESS.getCode());
        result.setMessage(CodeEnum.SUCCESS.getMsg());
        return result;
    }

    public static ApiResult success() {
        ApiResult result = new ApiResult();
        result.setData(null);
        result.setStatus(CodeEnum.SUCCESS.getCode());
        result.setMessage(CodeEnum.SUCCESS.getMsg());
        return result;
    }
}
