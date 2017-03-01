package com.guangbei.bosinone.api.domain.param;

/**
 * Created by xugang on 2017/3/1.
 */
public class RegisterParam {
    private String mobile;//手机号
    private String password;//密码
    private String confirmPassword;//重复密码
    private String smsCode;//验证码
    private String tongDunToken;//同盾token
    private String referrerMobile;//推荐人手机号

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getTongDunToken() {
        return tongDunToken;
    }

    public void setTongDunToken(String tongDunToken) {
        this.tongDunToken = tongDunToken;
    }

    public String getReferrerMobile() {
        return referrerMobile;
    }

    public void setReferrerMobile(String referrerMobile) {
        this.referrerMobile = referrerMobile;
    }
}
