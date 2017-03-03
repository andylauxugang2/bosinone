package com.guangbei.bosinone.core.service.impl;

import com.guangbei.bosinone.client.param.PPDGetValidCodeParam;
import com.guangbei.bosinone.client.param.PPDLoginParam;
import com.guangbei.bosinone.client.param.PPDValidCodeParam;
import com.guangbei.bosinone.client.result.PPDUserResult;
import com.guangbei.bosinone.core.common.SystemErrorEnum;
import com.guangbei.bosinone.core.manager.PPDUserManager;
import com.guangbei.bosinone.core.manager.exception.PPDException;
import com.guangbei.bosinone.core.manager.model.PPDCheckValidateCode;
import com.guangbei.bosinone.core.manager.model.PPDGetValidateCode;
import com.guangbei.bosinone.core.manager.model.PPDLogin;
import com.guangbei.bosinone.core.manager.model.PPDResponse;
import com.guangbei.bosinone.core.service.PPDUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xugang on 2017/3/2.
 */
@Service
public class PPDUserServiceImpl implements PPDUserService {
    private static final Logger logger = LoggerFactory.getLogger(PPDUserServiceImpl.class);

    @Autowired
    private PPDUserManager ppdUserManager;

    @Override
    public PPDUserResult getValidateCode(PPDGetValidCodeParam param) {
        PPDUserResult result = new PPDUserResult();
        try {
            PPDResponse<PPDGetValidateCode> response = ppdUserManager.getValidateCode(param.getUserId());
            result.setValidCode(response.getContent().getImage());
        } catch (PPDException e) {
            logger.error("获取拍拍贷登录验证码失败:", e);
            SystemErrorEnum.SYSTEM_ERROR.fillResult(result);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public PPDUserResult checkValidateCode(PPDValidCodeParam param) {
        PPDUserResult result = new PPDUserResult();
        try {
            PPDResponse<PPDCheckValidateCode> response = ppdUserManager.checkValidateCode(param.getUserId(), param.getValidCode());
            if (!response.success()) {
                logger.error("调用拍拍贷登录验证码验证接口返回失败,response=" + response + ",userId=" + param.getUserId());
                //返回接口提供的错误信息
                SystemErrorEnum.EXTERNAL_INTERFACE_ERROR.fillResult(result);
                result.setErrorMsg(response.getMessage());
            }
        } catch (PPDException e) {
            logger.error("验证拍拍贷登录验证码失败:", e);
            SystemErrorEnum.SYSTEM_ERROR.fillResult(result);
        }
        return result;
    }

    @Override
    public PPDUserResult login(PPDLoginParam param) {
        PPDUserResult result = new PPDUserResult();
        try {
            PPDResponse<PPDLogin> response = ppdUserManager.login(param.getUserId(), param.getUserName(),
                    param.getPassword(), param.isRememberMe(), param.getValidateCode());
            if (!response.success()) {
                logger.error("调用拍拍贷登录接口返回失败,response=" + response + ",userId=" + param.getUserId() + ",username=" + param.getUserName());
                //返回接口提供的错误信息
                SystemErrorEnum.EXTERNAL_INTERFACE_ERROR.fillResult(result);
                result.setErrorMsg(response.getMessage());
                return result;
            }

            PPDLogin login = response.getContent();
            PPDUserResult.PPDLoginResult ppdLoginResult = new PPDUserResult.PPDLoginResult();
            ppdLoginResult.setUserName(login.getUserName());
            ppdLoginResult.setIsIpEnabled(login.isIpEnabled());
            ppdLoginResult.setIsOpenQRCodeLogin(login.isOpenQRCodeLogin());
            ppdLoginResult.setIsOpenSmsLogin(login.isOpenSmsLogin());
            ppdLoginResult.setIsOpenStatusSetWaterMark(login.isOpenStatusSetWaterMark());
            ppdLoginResult.setRedirect(login.getRedirect());
            ppdLoginResult.setShowImgValidateCode(login.isShowImgValidateCode());
            ppdLoginResult.setPpDaiUniqueId(login.getPpDaiUniqueId());
            result.setPpdLoginResult(ppdLoginResult);
            return result;
        } catch (PPDException e) {
            logger.error("验证拍拍贷登录失败:", e);
            SystemErrorEnum.SYSTEM_ERROR.fillResult(result);
        }
        return result;
    }
}
