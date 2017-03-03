package com.guangbei.bosinone.client.param;

import java.io.Serializable;

/**
 * 参数基础类
 * Created by xugang on 16/11/2.
 */
public abstract class BaseParam implements Serializable {
    private String sourceIp;
    private String operator;
    private Long requestTime;
    private Long userId;

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
