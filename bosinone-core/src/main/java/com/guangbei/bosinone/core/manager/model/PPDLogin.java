package com.guangbei.bosinone.core.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xugang on 2017/3/2.
 */
public class PPDLogin {
    @JsonProperty(value = "UserName")
    private String userName;
    @JsonProperty(value = "Password")
    private String password;
    @JsonProperty(value = "ShowImgValidateCode")
    private Boolean showImgValidateCode;
    @JsonProperty(value = "IsIpEnabled")
    private Boolean isIpEnabled; //false
    @JsonProperty(value = "Redirect")
    private String redirect; //http://www.ppdai.com/account/lend
    @JsonProperty(value = "PPDai_UniqueId")
    private String ppDaiUniqueId; //f46eecde-f373-4448-9dc7-db5dcdaf06e8
    @JsonProperty(value = "IsOpenSmsLogin")
    private Boolean isOpenSmsLogin;
    @JsonProperty(value = "IsOpenQRCodeLogin")
    private Boolean isOpenQRCodeLogin;
    @JsonProperty(value = "IsOpenStatusSetWaterMark")
    private Boolean isOpenStatusSetWaterMark;

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

    public Boolean isShowImgValidateCode() {
        return showImgValidateCode;
    }

    public void setShowImgValidateCode(Boolean showImgValidateCode) {
        this.showImgValidateCode = showImgValidateCode;
    }

    public Boolean isIpEnabled() {
        return isIpEnabled;
    }

    public void setIsIpEnabled(Boolean isIpEnabled) {
        this.isIpEnabled = isIpEnabled;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getPpDaiUniqueId() {
        return ppDaiUniqueId;
    }

    public void setPpDaiUniqueId(String ppDaiUniqueId) {
        this.ppDaiUniqueId = ppDaiUniqueId;
    }

    public Boolean isOpenSmsLogin() {
        return isOpenSmsLogin;
    }

    public void setIsOpenSmsLogin(Boolean isOpenSmsLogin) {
        this.isOpenSmsLogin = isOpenSmsLogin;
    }

    public Boolean isOpenQRCodeLogin() {
        return isOpenQRCodeLogin;
    }

    public void setIsOpenQRCodeLogin(Boolean isOpenQRCodeLogin) {
        this.isOpenQRCodeLogin = isOpenQRCodeLogin;
    }

    public Boolean isOpenStatusSetWaterMark() {
        return isOpenStatusSetWaterMark;
    }

    public void setIsOpenStatusSetWaterMark(Boolean isOpenStatusSetWaterMark) {
        this.isOpenStatusSetWaterMark = isOpenStatusSetWaterMark;
    }
}
