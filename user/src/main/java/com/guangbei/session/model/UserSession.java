package com.guangbei.session.model;

/**
 * Created by xugang on 2017/1/4.
 */
public class UserSession {
    private String token;
    private Integer uid;
    private String mobile;

    public String getToken() {
        return token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
