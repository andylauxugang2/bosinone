package com.guangbei.bosinone.api.domain.param.ppd;

/**
 * 拍拍贷 登录参数
 * Created by xugang on 2017/3/1.
 */
public class PPDLoginParam {
    private String username;//已绑定手机/用户名/邮箱
    private String password;//密码
    private boolean rememberMe; //记住我

    private String validateCode;//验证码 密码输入错误过多 提示 存在风险，需要校验图片验证码

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
