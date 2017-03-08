package com.guangbei.bosinone.core.manager.impl;

import com.guangbei.bosinone.core.manager.common.RemoteCallHttpManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 将"qlui_loginuname=18611410103; domain=ac.ppdai.com; expires=Wed, 05-Apr-2017 06:07:14 GMT; path=/" 转为
     * "qlui_loginuname=18611410103" name-value对
     * @param cookies
     * @return
     */
    protected List<String> getCookie(List<String> cookies) {
        List<String> result = new ArrayList<>();
        for(String cookie : cookies){
            result.add(cookie.split(";")[0]);
        }
        return result;
    }

    protected Map<String,String> getCookieMap(List<String> cookies) {
        Map<String, String> result = new HashMap<>();
        for(String cookie : cookies){
            String entry = cookie.split(";")[0];
            String[] arr = entry.split("=");
            result.put(arr[0], arr[1]);
        }
        return result;
    }

    protected String getPpdUnameFromCookie(List<String> cookies) {
        Map<String, String> cookieMap = getCookieMap(cookies);
        return cookieMap.get("ppd_uname");
    }

    protected String getTokenFromCookie(List<String> cookies) {
        Map<String, String> cookieMap = getCookieMap(cookies);
        return cookieMap.get("token");
    }
}
