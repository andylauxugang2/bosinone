package com.guangbei.bosinone.core.service;

import com.guangbei.bosinone.client.param.PPDGetValidCodeParam;
import com.guangbei.bosinone.client.param.PPDLoginParam;
import com.guangbei.bosinone.client.param.PPDValidCodeParam;
import com.guangbei.bosinone.client.result.PPDUserResult;

/**
 * 拍拍贷
 * 用户登录 验证码
 * Created by xugang on 2017/3/2.
 */
public interface PPDUserService {

    /**
     * 获取拍拍贷登录页面验证码
     */
    PPDUserResult getValidateCode(PPDGetValidCodeParam param);

    /**
     * 验证拍拍贷登录页面验证码
     */
    PPDUserResult checkValidateCode(PPDValidCodeParam param);

    /**
     * 验证拍拍贷登录页面验证码
     */
    PPDUserResult login(PPDLoginParam param);
}
