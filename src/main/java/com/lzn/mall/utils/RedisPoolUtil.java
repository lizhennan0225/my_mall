package com.lzn.mall.utils;

import net.sf.jsqlparser.expression.LongValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by lzn on 2018/8/30.
 */
public class RedisPoolUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisPoolUtil.class);

    public static String set(String key, String value) {
        Jedis jedis = JedisPoolUtil.getResource();
        String returnValue = null;
        try {
            returnValue = jedis.set(key, value);
            JedisPoolUtil.returnResource(jedis);
        } catch (Exception e) {
            logger.error("设置建{}值{}失败", key, value, e);
            JedisPoolUtil.returnBrokenResource(jedis);
        }

        return null;
    }

    public static String setex(String key, String value, int seconds) {
        Jedis jedis = JedisPoolUtil.getResource();
        String returnValue = null;
        try {
            returnValue = jedis.setex(key, seconds, value);
            JedisPoolUtil.returnResource(jedis);
        } catch (Exception e) {
            logger.error("设置建{}值{}超时时间{}失败", key, value, seconds, e);
            JedisPoolUtil.returnBrokenResource(jedis);
        }

        return returnValue;
    }

    public static void expire(String key, int seconds) {
        Jedis jedis = JedisPoolUtil.getResource();
        try {
            jedis.expire(key, seconds);
            JedisPoolUtil.returnResource(jedis);
        } catch (Exception e) {
            logger.error("设置建超时时间失败", key, e);
            JedisPoolUtil.returnBrokenResource(jedis);
        }
    }

    public static String get(String key) {
        Jedis jedis = JedisPoolUtil.getResource();
        String value = null;
        try {
            value = jedis.get(key);
            JedisPoolUtil.returnResource(jedis);
        } catch (Exception e) {
            logger.error("获取建{}的值失败", key, e);
            JedisPoolUtil.returnBrokenResource(jedis);
        }

        return value;
    }


    public static Long del(String key) {
        Jedis jedis = JedisPoolUtil.getResource();
        Long reuslt = Long.valueOf(0);
        try {
            reuslt = jedis.del(key);
            JedisPoolUtil.returnResource(jedis);
        } catch (Exception e) {
            logger.error("删除失败", key, e);
            JedisPoolUtil.returnBrokenResource(jedis);
        }
        return reuslt;
    }
}
