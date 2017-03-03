package com.guangbei.bosinone.core.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guangbei.bosinone.core.common.PPDResponseCodeEnum;

/**
 * Created by xugang on 17/1/4.
 */
public class PPDResponse<T> {
    @JsonProperty(value = "Code")
    private int code; //响应状态码
    @JsonProperty(value = "Message")
    private String message; //响应信息
    @JsonProperty(value = "Content")
    private T content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean success() {
        return code == PPDResponseCodeEnum.OK.getCode();
    }

    @Override
    public String toString() {
        return "PPDResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
