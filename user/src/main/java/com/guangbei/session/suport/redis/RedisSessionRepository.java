package com.guangbei.session.suport.redis;

import com.guangbei.common.redis.JedisTemple;
import com.guangbei.session.SessionRepository;
import com.guangbei.session.utils.HttpConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class RedisSessionRepository implements SessionRepository<RedisSession> {

    @Autowired
    private JedisTemple jedisTemple;

    static final String DEFAULT_SESSION_REDIS_PREFIX = "houbank:session:";

    static final String CREATE_TIME = "createTime";

    static final String EXPIRED_TIME = "expiredTime";

    static final String LAST_ACCESSED_TIME = "lastAccessedTime";

    private String appName;

    private Integer maxIntervalTime = 1800;

    public RedisSessionRepository(JedisTemple jedisTemple) {
        this.jedisTemple = jedisTemple;
    }

    @Override
    public RedisSession createSession() {

        RedisSession session = new RedisSession();
        session.setFirst(true);
        session.setExpiredSeconds(maxIntervalTime);
        return session;
    }

    @Override
    public void save(RedisSession redisSession) {

        flush(redisSession);
        redisSession.setFirst(false);

    }

    @Override
    public RedisSession getSession(String sessionId) {

        return getSession(sessionId, true);
    }

    public RedisSession getSession(String sessionId, boolean isExpired) {

        String sessionKey = getSessionKey(sessionId);
        Map<String, Object> sessionMap = jedisTemple.hgetAll(sessionKey);
        if (sessionMap == null || sessionMap.entrySet().isEmpty()) return null;
        RedisSession redisSession = loadRedisSession(sessionId, sessionMap);
        if (isExpired && redisSession.isExpired()) return null;
        redisSession.setOriginalLastAccessTime(redisSession.getLastAccessedTime());
        return redisSession;
    }

    @Override
    public void delete(String sessionId) {
        jedisTemple.del(DEFAULT_SESSION_REDIS_PREFIX + sessionId);

    }

    private RedisSession loadRedisSession(String sessionId, Map<String, Object> sessionMap) {

        RedisSession redisSession = new RedisSession(sessionId);
        for (String key : sessionMap.keySet()) {
            if (CREATE_TIME.equals(key)) {
                redisSession.setCreationTime((Long) (sessionMap.get(key)));
            } else if (EXPIRED_TIME.equals(key)) {
                redisSession.setExpiredSeconds((Integer) (sessionMap.get(key)));
            } else if (LAST_ACCESSED_TIME.equals(key)) {
                redisSession.setLastAccessedTime((Long) (sessionMap.get(key)));
            } else if (StringUtils.isNotBlank(key) && key.startsWith(HttpConstants.SESSION_ATTR_PREFIX)) {
                redisSession.setAttribute(key.substring(HttpConstants.SESSION_ATTR_PREFIX.length()),
                        sessionMap.get(key));
            }
        }
        redisSession.getChangeAttrs().clear();
        return redisSession;
    }


    private void flush(RedisSession redisSession) {

        Map<String, Object> attrs = redisSession.getChangeAttrs();
        jedisTemple.hmset(getSessionKey(redisSession.getId()), attrs, (maxIntervalTime + 10 * 60));

        redisSession.setChangeAttrs(new HashMap<String, Object>());
    }

    private String getSessionKey(String sessionId) {
        String sessionKey = DEFAULT_SESSION_REDIS_PREFIX;
        if (StringUtils.isNotBlank(appName)) sessionKey += appName;
        return (sessionKey + sessionId);
    }


    public Integer getMaxIntervalTime() {
        return maxIntervalTime;
    }

    public void setMaxIntervalTime(Integer maxIntervalTime) {
        this.maxIntervalTime = maxIntervalTime;
    }

    public JedisTemple getJedisTemple() {
        return jedisTemple;
    }

    public void setJedisTemple(JedisTemple jedisTemple) {
        this.jedisTemple = jedisTemple;
    }

}
