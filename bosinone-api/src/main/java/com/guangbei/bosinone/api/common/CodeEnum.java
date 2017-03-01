package com.guangbei.bosinone.api.common;

/**
 * Created by xugang on 2017/3/1.
 */
public enum CodeEnum {

    SUCCESS("操作成功", 0),
    FAILED("操作失败", -1),
    Param("参数不正确", 2),
    InternetFail("网络异常", 3),
    MAXCOUNT("达到最大次数", 4),
    REPEAT("请不要重复操作", 5),
    SESSION_EXPIRED("登录过期，请重新登录", 5000),
    URL_FAIL("无访问权限", 6),
    REPEATREGIST("该号码已被注册", 5001),
    ACCOUNTNOTEXIST("该号码不存在", 5002),
    PASSWORDINCORRECT("手机号密码不匹配，请重新输入！", 5003),
    SMSCODENOTCORRECT("验证码输入错误，请重新输入", 5004),
    MOBILENOTREGIST("该账号尚未注册，请先注册", 5005),
    CANNOT_REPEAT_VERIFY("请勿重新验证", 5006),
    PLEASE_USE_REG_MOBILE("请使用注册手机号认证", 5007),
    PLEASE_USE_CORRECT_MOBILE("请输入正确的手机号", 5008),
    PLEASE_TYPE_MOBILE("请输入手机号", 5009);

    private String msg;

    private Integer code;

    CodeEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

}
