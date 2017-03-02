package com.guangbei.bosinone.api.controller;

import com.guangbei.bosinone.api.domain.param.ppd.PPDValidImgCodeParam;
import com.guangbei.bosinone.api.domain.result.ApiResult;
import com.guangbei.bosinone.api.domain.param.ppd.PPDLoginParam;
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

    /**
     * 登录拍拍贷账户
     * 如果登录存在风险,需要调用获取验证码接口+校验+再登录
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ApiResult login(@RequestBody final PPDLoginParam ppdLoginParam) {
        ApiResult<String> apiResult = new ApiResult<>();
        return apiResult;
    }

    /**
     * 获取验证码 返回 base64 格式
     */
    @RequestMapping(value = "/getValidCode", method = {RequestMethod.GET})
    public ApiResult getValidCode(HttpServletRequest request) {
        ApiResult<String> apiResult = new ApiResult<>();
        return apiResult;
    }

    /**
     * 登录拍拍贷账户
     * 如果登录存在风险,需要调用获取验证码接口+校验+再登录
     */
    @RequestMapping(value = "/validImgCode", method = {RequestMethod.POST})
    public ApiResult validImgCode(@RequestBody final PPDValidImgCodeParam ppdValidImgCodeParam) {
        ApiResult<String> apiResult = new ApiResult<>();
        return apiResult;
    }

}
