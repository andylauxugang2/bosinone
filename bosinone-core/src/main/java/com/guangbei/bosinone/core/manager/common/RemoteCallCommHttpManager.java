package com.guangbei.bosinone.core.manager.common;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 普通http请求 支持所有mino type
 * Created by xugang on 17/1/9.
 */
@Component
public class RemoteCallCommHttpManager extends RemoteCallHttpManager {

    @Resource(name = "restTemplate")
    protected RestTemplate restTemplate;

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
