package com.guangbei.bosinone.core.manager.impl;

import com.guangbei.bosinone.core.common.PPDWirelessAPIPropertyConfig;
import com.guangbei.bosinone.core.common.SystemErrorEnum;
import com.guangbei.bosinone.core.manager.PPDPersonalLoanManager;
import com.guangbei.bosinone.core.manager.exception.PPDException;
import com.guangbei.bosinone.core.manager.model.wireless.PPDWirelessGetPersonalLoanListRequest;
import com.guangbei.bosinone.core.manager.model.wireless.PPDWirelessGetPersonalLoanListResponse;
import com.guangbei.common.redis.JedisTemple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xugang on 2017/3/8.
 */
@Component
public class PPDPersonalLoanManagerImpl extends PPDRemoteCallHttpManagerWrapper implements PPDPersonalLoanManager {
    private static final Logger logger = LoggerFactory.getLogger(PPDPersonalLoanManagerImpl.class);
    @Resource
    private JedisTemple jedisTemple;

    @Resource
    protected PPDWirelessAPIPropertyConfig ppdWirelessAPIPropertyConfig;

    @Override
    public PPDWirelessGetPersonalLoanListResponse bidProductList(Long userId, String ppduname, PPDWirelessGetPersonalLoanListRequest request) throws PPDException {
        Map<String, Object> params = new HashMap<>();

        logger.info("开始调用拍拍贷无线api-bidProductList接口,userId={},ppduname={}", userId, ppduname);
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
            requestHeaders.add("X-PPD-APPID", "1");
            requestHeaders.add("X-PPD-APPVERSION", "4.0.0");
            requestHeaders.add("X-PPD-TIMESTAMP", "1588806231");
            requestHeaders.add("X-PPD-TOKEN", "09ff68aa-ba45-4892-80f6-cc18ca52f18c");
            requestHeaders.add("X-PPD-DEVICEID", "EC1EAF85-CE52-4636-8132-6446C5985D22");

            /*List<String> cookies = jedisTemple.lrangeAll(String.format(Constants.REDIS_KEY_PPD_USER_COOKIE, String.valueOf(userId)));
            if (!CollectionUtils.isEmpty(cookies)) {
                requestHeaders.put(HttpHeaders.COOKIE, getCookie(cookies));
            }*/

            ParameterizedTypeReference responseType = new ParameterizedTypeReference<PPDWirelessGetPersonalLoanListResponse>() {
            };
            ResponseEntity<PPDWirelessGetPersonalLoanListResponse> responseEntity = callRetry(ppdWirelessAPIPropertyConfig.getPersonalloanListUrl(), params, HttpMethod.POST, responseType, requestHeaders);
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
