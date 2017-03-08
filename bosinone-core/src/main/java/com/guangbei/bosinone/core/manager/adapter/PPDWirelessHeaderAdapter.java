package com.guangbei.bosinone.core.manager.adapter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * Created by xugang on 2017/3/8.
 */
@Component
public class PPDWirelessHeaderAdapter {

    public void setHttpHeader(String token, HttpHeaders requestHeaders) {
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        requestHeaders.add("X-PPD-APPID", "1");
        requestHeaders.add("X-PPD-APPVERSION", "4.0.0");
        requestHeaders.add("X-PPD-TIMESTAMP", String.valueOf(System.currentTimeMillis()).substring(0, 10));
        requestHeaders.add("X-PPD-TOKEN", token);
        requestHeaders.add("X-PPD-DEVICEID", "EA1EAF12-CC52-4126-8112-8796C5985C12");
    }
}
