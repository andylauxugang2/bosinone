package com.guangbei.bosinone.core.manager.impl;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.guangbei.bosinone.core.common.Constants;
import com.guangbei.bosinone.core.common.PPDWirelessAPIPropertyConfig;
import com.guangbei.bosinone.core.common.SystemErrorEnum;
import com.guangbei.bosinone.core.manager.PPDPersonalLoanManager;
import com.guangbei.bosinone.core.manager.adapter.PPDWirelessHeaderAdapter;
import com.guangbei.bosinone.core.manager.exception.PPDException;
import com.guangbei.bosinone.core.manager.model.wireless.PPDWirelessGetPersonalLoanListRequest;
import com.guangbei.bosinone.core.manager.model.wireless.PPDWirelessGetPersonalLoanListResponse;
import com.guangbei.bosinone.core.util.JsonUtil;
import com.guangbei.common.redis.JedisTemple;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by xugang on 2017/3/8.
 */
@Component
public class PPDPersonalLoanManagerImpl extends PPDRemoteCallHttpManagerWrapper implements PPDPersonalLoanManager {
    private static final Logger logger = LoggerFactory.getLogger(PPDPersonalLoanManagerImpl.class);
    @Resource
    private JedisTemple jedisTemple;

    @Autowired
    private PPDWirelessHeaderAdapter ppdWirelessHeaderAdapter;

    @Resource
    protected PPDWirelessAPIPropertyConfig ppdWirelessAPIPropertyConfig;

    @Override
    public PPDWirelessGetPersonalLoanListResponse bidProductList(Long userId, String ppduname, PPDWirelessGetPersonalLoanListRequest request) throws PPDException {
        try {
            //获取用户在ppd的token
            List<String> cookies = jedisTemple.lrangeAll(String.format(Constants.REDIS_KEY_PPD_USER_COOKIE, String.valueOf(userId)));
            if (CollectionUtils.isEmpty(cookies)) {
                logger.error("token获取失败,userId=" + userId + ",ppduname=" + ppduname);
                //返回自定义错误信息 供展示
                throw new PPDException(SystemErrorEnum.SYSTEM_ERROR);
            }
            String token = getTokenFromCookie(cookies);
            if (StringUtils.isEmpty(token)) {
                logger.error("token为空错误,userId=" + userId + ",ppduname=" + ppduname);
                //返回自定义错误信息 供展示
                throw new PPDException(SystemErrorEnum.SYSTEM_ERROR);
            }
            HttpHeaders requestHeaders = new HttpHeaders();
            ppdWirelessHeaderAdapter.setHttpHeader(token, requestHeaders);

            logger.info("开始调用拍拍贷无线api-bidProductList接口,userId={},ppduname={}", userId, ppduname);
            ParameterizedTypeReference responseType = new ParameterizedTypeReference<PPDWirelessGetPersonalLoanListResponse>() {
            };
            ResponseEntity<PPDWirelessGetPersonalLoanListResponse> responseEntity = callRetry(ppdWirelessAPIPropertyConfig.getPersonalloanListUrl(), JsonUtil.toJsonString(request), HttpMethod.POST, responseType, requestHeaders);
            int statusCode = responseEntity.getStatusCode().value();
            if (statusCode != HttpStatus.OK.value()) {
                logger.error("调用拍拍贷无线api-bidProductList接口状态非法,statusCode=" + statusCode + ",userId=" + userId + ",ppduname=" + ppduname);
                //返回自定义错误信息 供展示
                throw new PPDException(SystemErrorEnum.SYSTEM_ERROR);
            }
            PPDWirelessGetPersonalLoanListResponse response = responseEntity.getBody();
            logger.info("调用拍拍无线api-bidProductList接口成功,userId={}", userId);
            return response;
        } catch (Throwable e) {
            logger.error("调用拍拍贷无线api-bidProductList接口发生异常,userId=" + userId + ",ppduname=" + ppduname, e);
            throw new PPDException(e.getMessage());
        }
    }

}
