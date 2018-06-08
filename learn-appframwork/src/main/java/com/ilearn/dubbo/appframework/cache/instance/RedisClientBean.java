package com.ilearn.dubbo.appframework.cache.instance;

import com.ilearn.dubbo.appframework.cache.CacheService;
import com.ilearn.dubbo.appframework.log.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lihuan on 2018/5/21 0021.
 * redis客户端, key限定类型为String
 */
public class RedisClientBean implements CacheService {

    private final static Logger LOG = LoggerFactory.getLogger(RedisClientBean.class);

    private RedisTemplate redisTemplate;

    @Override
    public <T> boolean put(String key, T value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            LOG.error("invoke put error, key={}, error={},", key, LogUtils.stackTrace(e));
            return false;
        }
    }

    @Override
    public <T> boolean put(String key, T value, int exp) {
        try {
            redisTemplate.opsForValue().set(key, value, exp);
            return true;
        } catch (Exception e) {
            LOG.error("invoke put.exp error, key={}, error={},", key, LogUtils.stackTrace(e));
            return false;
        }
    }

    @Override
    public <T> T get(String key) {
        try {
            return key == null ? null : (T) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            LOG.error("invoke get error, key={}, error={},", key, LogUtils.stackTrace(e));
            return null;
        }

    }

    @Override
    public boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            LOG.error("invoke remove error, key={}, error={},", key, LogUtils.stackTrace(e));
            return false;
        }
    }

    @Override
    public boolean expire(String key, int exp) {
        try {
            if (exp > 0)
                redisTemplate.expire(key, exp, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            LOG.error("invoke expire error, key={}, error={},", key, LogUtils.stackTrace(e));
            return false;
        }
    }

    @Override
    public boolean isExist(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            LOG.error("invoke isExist error, key={}, error={},", key, LogUtils.stackTrace(e));
            return false;
        }
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
