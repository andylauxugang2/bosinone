package com.guangbei.bosinone.core.common;

/**
 * Created by xugang on 2017/3/5.
 */
public class Constants {

    //redis expire time
    public static final Integer REDIS_EXPIRE_TIME_PPD_USER_COOKIE = 60 * 60 * 24 * 365 * 5; //5年

    //redis key
    public static final String REDIS_KEY_PPD_USER_COOKIE = "ppd:cookie:userid:%s";
}
