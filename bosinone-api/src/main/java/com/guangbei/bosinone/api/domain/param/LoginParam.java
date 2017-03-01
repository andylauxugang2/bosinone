package com.guangbei.bosinone.api.domain.param;

/**
 * Created by xugang on 2017/3/1.
 */
public class LoginParam {
    private String mobile;//手机号
    private String password;//密码
    private String validateCode;//验证码
    private boolean rememberMe; //记住我

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

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
