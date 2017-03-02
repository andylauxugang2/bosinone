package com.guangbei.bosinone.core.manager;

import com.guangbei.bosinone.core.manager.exception.PPDException;
import com.guangbei.bosinone.core.manager.model.PPDGetValidateCode;

/**
 * 拍拍贷 用户相关 接口层操作
 * 登录 获取验证码 验证
 * Created by xugang on 17/1/4.
 */
public interface PPDUserManager {

    /**
     * 获取拍拍贷官网登录 所需验证码
     * @param userId 系统用户id
     * @return
     * @throws PPDException
     */
    PPDGetValidateCode getValidateCode(Integer userId) throws PPDException;

}
