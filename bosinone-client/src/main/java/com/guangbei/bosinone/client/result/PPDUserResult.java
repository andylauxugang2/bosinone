package com.guangbei.bosinone.client.result;


/**
 * Created by xugang on 16/11/2.
 */
public class PPDUserResult extends Result {

    private static final long serialVersionUID = 5087216344254682106L;
    private byte[] validCode; //验证码图片

    public byte[] getValidCode() {
        return validCode;
    }

    public void setValidCode(byte[] validCode) {
        this.validCode = validCode;
    }
}
