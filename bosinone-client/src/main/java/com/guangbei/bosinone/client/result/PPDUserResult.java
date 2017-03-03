package com.guangbei.bosinone.client.result;


/**
 * Created by xugang on 16/11/2.
 */
public class PPDUserResult extends Result {

    private static final long serialVersionUID = 5087216344254682106L;
    private byte[] validCode; //验证码图片
    private PPDLoginResult ppdLoginResult;

    public byte[] getValidCode() {
        return validCode;
    }

    public void setValidCode(byte[] validCode) {
        this.validCode = validCode;
    }

    public PPDLoginResult getPpdLoginResult() {
        return ppdLoginResult;
    }

    public void setPpdLoginResult(PPDLoginResult ppdLoginResult) {
        this.ppdLoginResult = ppdLoginResult;
    }

    public static class PPDLoginResult {
        private String userName;
        private boolean showImgValidateCode;
        private boolean isIpEnabled; //false
        private String redirect; //http://www.ppdai.com/account/lend
        private String ppDaiUniqueId; //f46eecde-f373-4448-9dc7-db5dcdaf06e8
        private boolean isOpenSmsLogin;
        private boolean isOpenQRCodeLogin;
        private boolean isOpenStatusSetWaterMark;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public boolean isShowImgValidateCode() {
            return showImgValidateCode;
        }

        public void setShowImgValidateCode(boolean showImgValidateCode) {
            this.showImgValidateCode = showImgValidateCode;
        }

        public boolean isIpEnabled() {
            return isIpEnabled;
        }

        public void setIsIpEnabled(boolean isIpEnabled) {
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

        public boolean isOpenSmsLogin() {
            return isOpenSmsLogin;
        }

        public void setIsOpenSmsLogin(boolean isOpenSmsLogin) {
            this.isOpenSmsLogin = isOpenSmsLogin;
        }

        public boolean isOpenQRCodeLogin() {
            return isOpenQRCodeLogin;
        }

        public void setIsOpenQRCodeLogin(boolean isOpenQRCodeLogin) {
            this.isOpenQRCodeLogin = isOpenQRCodeLogin;
        }

        public boolean isOpenStatusSetWaterMark() {
            return isOpenStatusSetWaterMark;
        }

        public void setIsOpenStatusSetWaterMark(boolean isOpenStatusSetWaterMark) {
            this.isOpenStatusSetWaterMark = isOpenStatusSetWaterMark;
        }
    }

}
