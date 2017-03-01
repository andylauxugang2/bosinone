package com.guangbei.bosinone.api.controller;

import com.guangbei.bosinone.api.domain.UserDTO;
import com.guangbei.bosinone.api.domain.param.ChangePasswordParam;
import com.guangbei.bosinone.api.domain.param.LoginParam;
import com.guangbei.bosinone.api.domain.param.RegisterParam;
import com.guangbei.bosinone.api.domain.result.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户
 * 登录 注册
 * Created by xugang on 2017/3/1.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/getUser", method = {RequestMethod.GET})
    public ApiResult<UserDTO> getUser(HttpServletRequest request) {
        ApiResult<UserDTO> apiResult = new ApiResult<>();
        return apiResult;
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/changePassword", method = {RequestMethod.POST})
    public ApiResult<Void> changePassword(@RequestBody final ChangePasswordParam changePasswordParam,
                                          HttpServletRequest request) {
        ApiResult<Void> apiResult = new ApiResult<>();
        return apiResult;
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult<UserDTO> register(@RequestBody RegisterParam registerParam) {
        ApiResult<UserDTO> apiResult = new ApiResult<>();
        return apiResult;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<UserDTO> appLogin(@RequestBody LoginParam loginParam) {
        ApiResult<UserDTO> apiResult = new ApiResult<>();
        return apiResult;
    }
}
