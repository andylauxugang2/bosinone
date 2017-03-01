package com.guangbei.session.config;

import com.guangbei.common.redis.JedisTemple;
import com.guangbei.session.http.CookieSerializer;
import com.guangbei.session.http.HttpSessionFilter;
import com.guangbei.session.suport.redis.RedisSessionRepository;
import com.guangbei.session.utils.HttpConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class RedisHttpSessionConfiguration {

    private Integer expiredTime = 60 * 60;
    private String domainName;
    private Integer cookieMaxAge = -1;

    @Bean
    public RedisSessionRepository redisSessionRepository(@Qualifier("jedisTemple") JedisTemple jedisTemple) {
        RedisSessionRepository sessionRepository = new RedisSessionRepository(jedisTemple);
        sessionRepository.setMaxIntervalTime(expiredTime);
        return sessionRepository;
    }

    @Bean
    public HttpSessionFilter httpSessinFilter(@Qualifier("redisSessionRepository")
                                              RedisSessionRepository redisSessionRepository) {
        CookieSerializer.cookieMaxAge = cookieMaxAge;
        CookieSerializer.domainName = domainName;
        CookieSerializer.cookieName = HttpConstants.COOKIE_NAME;
        return new HttpSessionFilter(redisSessionRepository);
    }

    public Integer getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Integer expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Integer getCookieMaxAge() {
        return cookieMaxAge;
    }

    public void setCookieMaxAge(Integer cookieMaxAge) {
        this.cookieMaxAge = cookieMaxAge;
    }

}
