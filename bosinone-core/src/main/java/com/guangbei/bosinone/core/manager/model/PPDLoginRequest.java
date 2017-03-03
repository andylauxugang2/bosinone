package com.guangbei.bosinone.core.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guangbei.bosinone.core.common.http.param.AbstractParamMap;

/**
 * Created by xugang on 17/1/4.
 */
public class PPDLoginRequest extends AbstractParamMap {
    private String userName; //18611410103
    private String password;
    private String ssAsync = "true";
    private String rememberMe;
    private String validateCode;

    @JsonProperty(value = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty(value = "Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty(value = "IsAsync")
    public String getSsAsync() {
        return ssAsync;
    }

    public void setSsAsync(String ssAsync) {
        this.ssAsync = ssAsync;
    }

    @JsonProperty(value = "RememberMe")
    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    @JsonProperty(value = "ValidateCode")
    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
