package com.guangbei.bosinone.core.manager;

import com.guangbei.bosinone.core.common.Constants;
import com.guangbei.bosinone.core.common.PropertyConfig;
import com.guangbei.bosinone.core.common.SystemErrorEnum;
import com.guangbei.bosinone.core.manager.exception.PPDException;
import com.guangbei.bosinone.core.manager.model.*;
import com.guangbei.common.redis.JedisTemple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拍拍贷 接口调用 非OpenAPI
 * Created by xugang on 2017/3/2.
 */
@Component
public class PPDUserManagerImpl extends PPDRemoteCallHttpManagerWrapper implements PPDUserManager {

    private static final Logger logger = LoggerFactory.getLogger(PPDUserManagerImpl.class);

    @Resource
    private JedisTemple jedisTemple;

    @Resource
    protected PropertyConfig propertyConfig;

    @Override
    public PPDResponse<PPDGetValidateCode> getValidateCode(Long userId) throws PPDException {
        Map<String, Object> params = new HashMap<>();
        PPDGetValidCodeRequest request = new PPDGetValidCodeRequest();
        request.setTmp(String.valueOf(System.currentTimeMillis()));
        params.putAll(request.toParamMap());

        logger.info("开始调用拍拍贷获取验证码接口,userId={}", userId);
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            List<String> cookies = jedisTemple.lrangeAll(String.format(Constants.REDIS_KEY_PPD_USER_COOKIE, String.valueOf(userId)));
            if(!CollectionUtils.isEmpty(cookies)){
                requestHeaders.put(HttpHeaders.COOKIE, getCookie(cookies));
            }

            ParameterizedTypeReference responseType = new ParameterizedTypeReference<byte[]>() {
            };
            PPDResponse response = new PPDResponse();
            ResponseEntity<byte[]> responseEntity = call(propertyConfig.getPpdWebSiteValidateCodeUrl(), params, HttpMethod.GET, responseType, requestHeaders);
            int statusCode = responseEntity.getStatusCode().value();
            if (statusCode != HttpStatus.OK.value()) {
                logger.error("调用拍拍贷获取验证码接口状态非法,statusCode=" + statusCode + ",userId=" + userId);
                throw new PPDException(SystemErrorEnum.SYSTEM_ERROR);
            }
            List<String> _cookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);
            //保存user cookie 到缓存 主要是替换login风险响应的仅有 qlui_loginuname=13313066864 的情况
            if(!CollectionUtils.isEmpty(_cookies)){
                jedisTemple.del(String.format(Constants.REDIS_KEY_PPD_USER_COOKIE, String.valueOf(userId)));
                jedisTemple.lpush(String.format(Constants.REDIS_KEY_PPD_USER_COOKIE, String.valueOf(userId)), _cookies, Constants.REDIS_EXPIRE_TIME_PPD_USER_COOKIE);
            }

            byte[] image = responseEntity.getBody();
            if (image == null || image.length == 0) {
                logger.error("调用拍拍贷查询验证码接口获取结果为空:userId=" + userId);
                throw new PPDException(SystemErrorEnum.SYSTEM_ERROR);
            }
            PPDGetValidateCode ppdGetValidateCode = new PPDGetValidateCode();
            ppdGetValidateCode.setImage(image);
            logger.info("调用拍拍贷获取验证码接口成功,userId={}", userId);
            response.setContent(ppdGetValidateCode);
            return response;
        } catch (Throwable e) {
            logger.error("调用拍拍贷获取验证码接口发生异常,userId=" + userId, e);
            throw new PPDException(e.getMessage());
        }
    }

    @Override
    public PPDResponse<PPDCheckValidateCode> checkValidateCode(Long userId, String validCode) {
        Map<String, Object> params = new HashMap<>();
        PPDCheckValidCodeRequest request = new PPDCheckValidCodeRequest();
        request.setName(PPDCheckValidCodeRequest.PARAM_NAME_VALUE);
        request.setValue(validCode);
        request.set_(String.valueOf(System.currentTimeMillis()));
        params.putAll(request.toParamMap());

        logger.info("开始调用拍拍贷登录验证码验证接口,userId={}", userId);
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            List<String> cookies = jedisTemple.lrangeAll(String.format(Constants.REDIS_KEY_PPD_USER_COOKIE, String.valueOf(userId)));
            if(!CollectionUtils.isEmpty(cookies)) requestHeaders.put(HttpHeaders.COOKIE, getCookie(cookies));

            ParameterizedTypeReference responseType = new ParameterizedTypeReference<PPDResponse>() {
            };
            ResponseEntity<PPDResponse> responseEntity = callRetry(propertyConfig.getPpdWebSiteCheckValidateCodeUrl(), params, HttpMethod.POST, responseType, requestHeaders);
            int statusCode = responseEntity.getStatusCode().value();
            if (statusCode != HttpStatus.OK.value()) {
                logger.error("调用拍拍贷登录验证码验证接口状态非法,statusCode=" + statusCode + ",userId=" + userId);
                //返回自定义错误信息 供展示
                throw new PPDException(SystemErrorEnum.SYSTEM_ERROR);
            }
            PPDResponse response = responseEntity.getBody();
            logger.info("调用拍拍登录验证码验证接口成功,userId={}", userId);
            PPDCheckValidateCode ppdCheckValidateCode = new PPDCheckValidateCode();
            response.setContent(ppdCheckValidateCode);
            return response;
        } catch (Throwable e) {
            logger.error("调用拍拍贷登录验证码验证接口发生异常,userId=" + userId, e);
            throw new PPDException(e.getMessage());
        }
    }

    @Override
    public PPDResponse<PPDLogin> login(Long userId, String userName, String password, boolean rememberMe, String validateCode) {
        Map<String, Object> params = new HashMap<>();
        PPDLoginRequest request = new PPDLoginRequest();
        request.setUserName(userName);
        request.setPassword(password); //TODO 加解密
        request.setRememberMe(String.valueOf(rememberMe));
        request.setValidateCode(validateCode);
        params.putAll(request.toParamMap());

        logger.info("开始调用拍拍贷登录接口,userId={},username={}", userId, userName);
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            List<String> cookies = jedisTemple.lrangeAll(String.format(Constants.REDIS_KEY_PPD_USER_COOKIE, String.valueOf(userId)));
            if(!CollectionUtils.isEmpty(cookies)){
                requestHeaders.put(HttpHeaders.COOKIE, getCookie(cookies));
            }
            ParameterizedTypeReference responseType = new ParameterizedTypeReference<PPDResponse<PPDLogin>>() {
            };
            ResponseEntity<PPDResponse<PPDLogin>> responseEntity = callRetry(propertyConfig.getPpdWebSiteLoginUrl(), params, HttpMethod.POST, responseType, requestHeaders);
            int statusCode = responseEntity.getStatusCode().value();
            if (statusCode != HttpStatus.OK.value()) {
                logger.error("调用拍拍贷登录接口状态非法,statusCode=" + statusCode + ",userId=" + userId + ",usename=" + userName);
                //返回自定义错误信息 供展示
                throw new PPDException(SystemErrorEnum.SYSTEM_ERROR);
            }
            List<String> _cookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);
            //保存user cookie 到缓存
            jedisTemple.del(String.format(Constants.REDIS_KEY_PPD_USER_COOKIE, String.valueOf(userId)));
            jedisTemple.lpush(String.format(Constants.REDIS_KEY_PPD_USER_COOKIE, String.valueOf(userId)), _cookies, Constants.REDIS_EXPIRE_TIME_PPD_USER_COOKIE);
            PPDResponse<PPDLogin> response = responseEntity.getBody();
            logger.info("调用拍拍登录接口成功,userId={}", userId);
            return response;
        } catch (Throwable e) {
            logger.error("调用拍拍贷登录验证码验证接口发生异常,userId=" + userId, e);
            throw new PPDException(e.getMessage());
        }
    }
}
