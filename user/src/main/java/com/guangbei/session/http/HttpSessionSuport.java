package com.guangbei.session.http;

import com.guangbei.common.exception.ErrorMessageConstants;
import com.guangbei.session.exception.ParamError;
import com.guangbei.session.suport.redis.RedisSession;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class HttpSessionSuport {


    private static ThreadLocal<RedisSession> threadLocal = new ThreadLocal<>();


    public static void set(RedisSession redisSession) {

        threadLocal.set(redisSession);
    }

    public static RedisSession get() {

        return threadLocal.get();
    }

    public static void clear() {

        threadLocal.set(null);
    }

    public static void reCreate(String sessionID) {
        RedisSession redisSession = get();
        if (redisSession != null && !redisSession.isFirst()) {
            redisSession.setSessionId(sessionID);
            redisSession.setFirst(true);
            Set<String> attributeNames = redisSession.getAttributeNames();
            for (String attr : attributeNames) {
                redisSession.setAttribute(attr, redisSession.getAttribute(attr));
            }
        }
    }

    public static void setAttribute(String attributeName, Object attributeValue) {
        if (StringUtils.isBlank(attributeName)
                || attributeValue == null
                ) {
            throw new ParamError(ErrorMessageConstants.PARRAM_ERROR);
        }
        RedisSession redisSession = get();
        redisSession.setAttribute(attributeName, attributeValue);
    }

    public static void removeAttribute(String attributeName) {
        if (StringUtils.isBlank(attributeName)
                ) {
            throw new ParamError(ErrorMessageConstants.PARRAM_ERROR);
        }
        RedisSession redisSession = get();
        redisSession.removeAttribute(attributeName);
    }

    public static <T> T getAttribute(String attributeName) {
        if (StringUtils.isBlank(attributeName)) {
            throw new ParamError(ErrorMessageConstants.PARRAM_ERROR);
        }
        RedisSession redisSession = get();
        //threadLocal跨线程返回为null
        if (redisSession == null) {
            return null;
        }
        return (T) redisSession.getAttribute(attributeName);
    }

    public static void setSessionId(String sessionId, HttpServletResponse response) {
        RedisSession redisSession = get();
        redisSession.setSessionId(sessionId);
        redisSession.handlClientStore(response);
    }

    public static void delSession() {
        RedisSession redisSession = get();
        redisSession.setDelete(true);
    }

}

