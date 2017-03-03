package com.guangbei.bosinone.core.common;

/**
 * 拍拍贷 接口返回 错误码映射
 * Created by xugang on 2017/1/10.
 */
public enum PPDResponseCodeEnum {

    OK(1, "ok", "操作成功"),
    FAIL(0, "fail", "操作失败"),
    UNKOWN(-999, "未知错误", "网络错误，请稍后再试");

    private int code;
    private String desc;
    private String descForClient;

    PPDResponseCodeEnum(int code, String desc, String descForClient) {
        this.code = code;
        this.desc = desc;
        this.descForClient = descForClient;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescForClient() {
        return descForClient;
    }

    /**
     * 根据枚举码获取枚举
     *
     * @param code 枚举码
     * @return 枚举
     */
    public static PPDResponseCodeEnum getByCode(int code) {
        for (PPDResponseCodeEnum item : PPDResponseCodeEnum.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return UNKOWN;
    }
}
