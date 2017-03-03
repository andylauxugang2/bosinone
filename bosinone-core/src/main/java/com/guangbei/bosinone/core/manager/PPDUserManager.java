package com.guangbei.bosinone.core.manager;

import com.guangbei.bosinone.core.manager.exception.PPDException;
import com.guangbei.bosinone.core.manager.model.PPDCheckValidateCode;
import com.guangbei.bosinone.core.manager.model.PPDGetValidateCode;
import com.guangbei.bosinone.core.manager.model.PPDLogin;
import com.guangbei.bosinone.core.manager.model.PPDResponse;

/**
 * 拍拍贷 用户相关 接口层操作
 * 登录 获取验证码 验证
 * Created by xugang on 17/1/4.
 */
public interface PPDUserManager {

    /**
     * 获取拍拍贷官网登录 所需验证码
     *
     * @param userId 系统用户id
     * @return
     * @throws PPDException
     */
    PPDResponse<PPDGetValidateCode> getValidateCode(Long userId) throws PPDException;

    /**
     * 验证拍拍贷官网登录验证码
     *
     * @param userId
     * @param validCode
     * @return
     */
    PPDResponse<PPDCheckValidateCode> checkValidateCode(Long userId, String validCode);

    /**
     * 拍拍贷官网登录
     *
     * @param userId
     * @param userName
     * @param password
     * @param rememberMe
     * @param validateCode
     * @return
     */
    PPDResponse<PPDLogin> login(Long userId, String userName, String password, boolean rememberMe, String validateCode);
}
