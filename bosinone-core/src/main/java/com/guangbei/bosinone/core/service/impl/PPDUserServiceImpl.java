package com.guangbei.bosinone.core.service.impl;

import com.guangbei.bosinone.client.param.PPDGetValidCodeParam;
import com.guangbei.bosinone.client.result.PPDUserResult;
import com.guangbei.bosinone.core.common.SystemErrorEnum;
import com.guangbei.bosinone.core.manager.PPDUserManager;
import com.guangbei.bosinone.core.manager.exception.PPDException;
import com.guangbei.bosinone.core.manager.model.PPDGetValidateCode;
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
            PPDGetValidateCode ppdGetValidateCode = ppdUserManager.getValidateCode(param.getUserId());
            result.setValidCode(ppdGetValidateCode.getImage());
        } catch (PPDException e) {
            logger.error("获取拍拍贷登录验证码失败:", e);
            SystemErrorEnum.SYSTEM_ERROR.fillResult(result);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }
}
