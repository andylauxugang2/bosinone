package com.guangbei.bosinone.core.manager;

import com.guangbei.bosinone.core.manager.common.RemoteCallHttpManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by xugang on 17/1/9.
 */
public class PPDRemoteCallHttpManagerWrapper extends RemoteCallHttpManager {
    
    private static final Logger logger = LoggerFactory.getLogger(PPDRemoteCallHttpManagerWrapper.class);

    public static final String HTTP_TYPE_COMM = "comm_http";
    public static final String HTTP_TYPE_NOTSSL = "not_ssl";

    @Resource
    private RemoteCallHttpManager remoteCallCommHttpManager;
    @Resource
    private RemoteCallHttpManager remoteCallHttpsNotSSLManager;

    @Override
    public RestTemplate getRestTemplate() {
        //TODO 根据枚举参数 选择合适的restTemplate
        return remoteCallHttpsNotSSLManager.getRestTemplate();
    }
}
