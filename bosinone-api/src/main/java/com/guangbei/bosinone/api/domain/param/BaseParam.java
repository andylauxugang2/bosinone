package com.guangbei.bosinone.api.domain.param;

/**
 * 全部明文访问,往后会https加密即可,如果是openAPI方式提供,需要添加mchId参数和sign参数签名,并全加密
 * Created by xugang on 2017/3/1.
 */
public abstract class BaseParam {
    private String timestamp; //请求时间戳
    private String version;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
