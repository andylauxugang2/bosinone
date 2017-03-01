package com.guangbei.bosinone.api.domain.param;

/**
 * Created by xugang on 2017/3/1.
 */
public class ChangePasswordParam {

    private String confirmPassword;
    private String password;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
