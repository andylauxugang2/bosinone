package com.guangbei.bosinone.client.param;

/**
 * Created by xugang on 16/11/2.
 */
public class PPDLoginParam extends PPDParam {

    private static final long serialVersionUID = 6448965965863910874L;

    private String userName;
    private String password;
    private boolean rememberMe;
    private String validateCode; //验证码 正确登录不用

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
