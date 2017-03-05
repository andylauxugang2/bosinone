package com.guangbei.bosinone.core.manager.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

/**
 * http 远程调用
 * 重试
 * Created by xugang on 2017/3/2.
 */
public abstract class RemoteCallHttpManager {
    private static final Logger logger = LoggerFactory.getLogger(RemoteCallHttpManager.class);

    private RetryTemplate retryTemplate = new RetryTemplate();

    //适用于上传文件操作 form表单上传等
    protected String postForObject(String url, Map<String, Object> paramMap) throws Exception {
        LocalDateTime startDateTime = LocalDateTime.now();
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.setAll(paramMap);
        String result = getRestTemplate().postForObject(url, param, String.class);
        //埋点请求花费时间
        LocalDateTime endDateTime = LocalDateTime.now();
        logger.info("请求接口成功,耗时埋点:url={},times={}", url, Duration.between(startDateTime, endDateTime));
        return result;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000))
    protected <T> ResponseEntity<T> callRetry(String url, Map<String, Object> paramMap, HttpMethod method, ParameterizedTypeReference<T> responseType, HttpHeaders headers, Object... uriVariables) throws Exception {
        //抛出任何Exception都会重试，直到策略终止，调用recoveryCallback
        ResponseEntity<T> result = retryTemplate.execute(context -> call(url, paramMap, method, responseType, headers, uriVariables));
        return result;
    }

    protected <T> ResponseEntity<T> call(String url, Map<String, Object> paramMap, HttpMethod method, ParameterizedTypeReference<T> responseType, HttpHeaders headers, Object... uriVariables) throws Exception {
        LocalDateTime startDateTime = LocalDateTime.now();
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.setAll(paramMap);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
        ResponseEntity<T> result = getRestTemplate().exchange(url, method, httpEntity, responseType, uriVariables);
        //埋点请求花费时间
        LocalDateTime endDateTime = LocalDateTime.now();
        logger.info("请求接口成功,耗时埋点:url={},times={}", url, Duration.between(startDateTime, endDateTime));
        return result;
    }

    /**
     * 获取resttemplate对象,根据子类设置为特定类型,比如notSSL的https和http等
     *
     * @return
     */
    public abstract RestTemplate getRestTemplate();
}
