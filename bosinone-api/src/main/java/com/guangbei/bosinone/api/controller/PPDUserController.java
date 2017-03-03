package com.guangbei.bosinone.api.controller;

import com.guangbei.bosinone.api.common.CodeEnum;
import com.guangbei.bosinone.api.domain.param.ppd.PPDCommLoginParam;
import com.guangbei.bosinone.api.domain.param.ppd.PPDValidImgCodeParam;
import com.guangbei.bosinone.api.domain.result.ApiResult;
import com.guangbei.bosinone.client.param.PPDGetValidCodeParam;
import com.guangbei.bosinone.client.param.PPDLoginParam;
import com.guangbei.bosinone.client.param.PPDValidCodeParam;
import com.guangbei.bosinone.client.result.PPDUserResult;
import com.guangbei.bosinone.core.service.PPDUserService;
import com.guangbei.util.net.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 拍拍贷用户
 * TODO 以后改为 渠道url 如拍拍贷渠道号是12345,则url为/12345
 * 获取验证码
 * 登录
 * Created by xugang on 2017/3/1.
 */
@RestController
@RequestMapping("/ppd")
public class PPDUserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(PPDUserController.class);

    @Autowired
    private PPDUserService ppdUserService;

    /**
     * 登录拍拍贷账户 用户密码登录方式
     * 如果登录存在风险,需要调用获取验证码接口+校验+再登录
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ApiResult login(@RequestBody final PPDCommLoginParam ppdCommLoginParam) {
        PPDLoginParam param = new PPDLoginParam();
        param.setUserId(1L);
        param.setUserName(ppdCommLoginParam.getUsername());
        param.setPassword(ppdCommLoginParam.getPassword());
        param.setRememberMe(ppdCommLoginParam.isRememberMe());
        param.setValidateCode(ppdCommLoginParam.getValidateCode());

        PPDUserResult result = ppdUserService.login(param);
        if (result.isSuccess()) {
            logger.info("拍拍贷登录成功,userId={},username={}", param.getUserId(), param.getUserName());
            return ApiResult.success();
        } else {
            return ApiResult.createResult(CodeEnum.FAILED, result.getErrorMsg());
        }
    }

    /**
     * 获取验证码 返回 base64 格式
     */
    @RequestMapping(value = "/getValidCode", method = {RequestMethod.GET})
    public ApiResult getValidCode(HttpServletRequest request) {
        PPDGetValidCodeParam param = new PPDGetValidCodeParam();
        param.setUserId(1L);
        PPDUserResult result = ppdUserService.getValidateCode(param);

        if (result.isSuccess()) {
            logger.info("获取验证码成功,userId={}", param.getUserId());
            return ApiResult.success(ImageUtils.encodeBytesToBase64(result.getValidCode()));
        } else {
            return ApiResult.createResult(CodeEnum.FAILED, result.getErrorMsg());
        }
    }

    /**
     * 登录拍拍贷账户
     * 如果登录存在风险,需要调用获取验证码接口+校验+再登录
     */
    @RequestMapping(value = "/validImgCode", method = {RequestMethod.POST})
    public ApiResult validImgCode(@RequestBody final PPDValidImgCodeParam ppdValidImgCodeParam) {
        PPDValidCodeParam param = new PPDValidCodeParam();
        param.setUserId(1L);
        param.setValidCode(ppdValidImgCodeParam.getValidCode());
        PPDUserResult result = ppdUserService.checkValidateCode(param);
        if (result.isSuccess()) {
            logger.info("登录验证码验证成功,userId={},validCode={}", param.getUserId(), param.getValidCode());
            return ApiResult.success();
        } else {
            return ApiResult.createResult(CodeEnum.FAILED, result.getErrorMsg());
        }
    }

}
