package com.guangbei.session.utils;

import com.guangbei.session.http.CookieHttpStrategy;
import com.guangbei.session.http.HeaderHttpStrategy;
import com.guangbei.session.http.HttpStrategy;

public class HttpStrategyUtil {

    public static HttpStrategy headerStrategy = new HeaderHttpStrategy();
    public static HttpStrategy cookieStrategy = new CookieHttpStrategy();

}
