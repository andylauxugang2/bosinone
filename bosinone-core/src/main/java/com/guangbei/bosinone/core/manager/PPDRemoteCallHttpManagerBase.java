package com.guangbei.bosinone.core.manager;

import com.guangbei.bosinone.core.manager.common.RemoteCallHttpManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by xugang on 17/1/9.
 */
public class PPDRemoteCallHttpManagerBase extends RemoteCallHttpManager {

    private static final Logger logger = LoggerFactory.getLogger(PPDRemoteCallHttpManagerBase.class);

    @Resource(name = "restTemplate")
    protected RestTemplate restTemplate;

    @Override
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
