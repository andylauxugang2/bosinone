package com.guangbei.bosinone.core.common;

import java.io.Serializable;

/**
 * 常量参数配置类 可以迁到动态开关中
 * Created by xugang on 2016/9/12.
 */
public class PropertyConfig implements Serializable {

    private static final long serialVersionUID = -5154423798525674982L;

    /**
     * ppd官网地址 默认值为 "https://ac.ppdai.com"
     */
    private String ppdWebSiteUrl = "https://ac.ppdai.com";

    /**
     * 获取登录验证码 接口地址
     */
    private String ppdWebSiteValidateCodeUrl = "ValidateCode/Image";

    /**
     * 获取登录 接口地址
     */
    private String ppdWebSiteLoginUrl = "User/Login";

    /**
     * 验证登录验证码 接口地址
     */
    private String ppdWebSiteCheckValidateCodeUrl = "registercheck";

    public String getPpdWebSiteUrl() {
        return ppdWebSiteUrl;
    }

    public void setPpdWebSiteUrl(String ppdWebSiteUrl) {
        this.ppdWebSiteUrl = ppdWebSiteUrl;
    }

    public String getPpdWebSiteValidateCodeUrl() {
        return ppdWebSiteValidateCodeUrl;
    }

    public void setPpdWebSiteValidateCodeUrl(String ppdWebSiteValidateCodeUrl) {
        this.ppdWebSiteValidateCodeUrl = ppdWebSiteValidateCodeUrl;
    }

    public String getPpdWebSiteCheckValidateCodeUrl() {
        return ppdWebSiteCheckValidateCodeUrl;
    }

    public void setPpdWebSiteCheckValidateCodeUrl(String ppdWebSiteCheckValidateCodeUrl) {
        this.ppdWebSiteCheckValidateCodeUrl = ppdWebSiteCheckValidateCodeUrl;
    }

    public String getPpdWebSiteLoginUrl() {
        return ppdWebSiteLoginUrl;
    }

    public void setPpdWebSiteLoginUrl(String ppdWebSiteLoginUrl) {
        this.ppdWebSiteLoginUrl = ppdWebSiteLoginUrl;
    }
}
