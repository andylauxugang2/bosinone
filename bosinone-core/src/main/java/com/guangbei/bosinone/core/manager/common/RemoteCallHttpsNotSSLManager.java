package com.guangbei.bosinone.core.manager.common;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 跨https ssl证书
 * Created by xugang on 17/1/9.
 */
@Component
public class RemoteCallHttpsNotSSLManager extends RemoteCallHttpManager {

    @Resource(name = "notSSLRestTemplate")
    protected RestTemplate notSSLRestTemplate;

    @Override
    public RestTemplate getRestTemplate() {
        return notSSLRestTemplate;
    }

}
