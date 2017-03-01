package com.guangbei.common.redis;

import com.guangbei.common.exception.ErrorMessageConstants;
import com.guangbei.common.exception.InitError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

public class JedisFactory {

    private static final Logger logger = LoggerFactory.getLogger(JedisFactory.class);

    private int timeOut = 30000;

    private Object lock = new Object();

    private JedisPoolConfig jedisPoolConfig;

    private String host;

    private Integer port;

    private String password;

    private Pool<Jedis> jedisPool;

    private volatile boolean shutdown = false;


    public void init() {

        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeOut, password);
        Jedis jedis = jedisPool.getResource();
        if (null == jedis || !("PONG".equals(jedis.ping()))) {
            throw new InitError(ErrorMessageConstants.INIT_ERROR);
        }
    }

    public Jedis getSource() {
        Jedis jedis = null;
        int count = 0;
        do {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
                if (jedis != null) break;
            }
            if ((jedisPool == null || jedis == null) && !shutdown) {
                synchronized (lock) {
                    if (jedisPool == null) {
                        init();
                    }
                }
            }
            count++;
        } while (count < 3);

        return jedis;
    }

    public void destory() {
        synchronized (lock) {
            if (jedisPool != null) {
                jedisPool.close();
                shutdown = true;
            }
        }
    }

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
