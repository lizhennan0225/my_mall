package com.lzn.mall.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by lzn on 2018/8/30.
 */
public class JedisPoolUtil {
    private static String ip = PropertiesUtil.getProperty("redis.ip", "20");
    private static String port = PropertiesUtil.getProperty("redis.port", "20");
    private static String maxTotal = PropertiesUtil.getProperty("redis.max.total", "20"); //最大连接数
    private static String maxIdel = PropertiesUtil.getProperty("redis.max.idel", "10");//最大空闲数
    private static String minIdel = PropertiesUtil.getProperty("redis.min.idel", "2");//最小空闲数
    private static String testOnBorrow = PropertiesUtil.getProperty("redis.test.borrow", "true");
    private static String testOnReturn = PropertiesUtil.getProperty("redis.test.return", "false");
    private static JedisPool jedisPool;


    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.valueOf(maxTotal));
        config.setMaxIdle(Integer.valueOf(maxIdel));
        config.setMinIdle(Integer.valueOf(minIdel));
        config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        config.setTestOnReturn(Boolean.valueOf(testOnReturn));
        jedisPool = new JedisPool(config, ip, Integer.valueOf(port));
    }

    public static Jedis getResource(){
        return jedisPool.getResource();
    }

    public static void returnResource(Jedis jedis){
        jedisPool.returnResource(jedis);

    }

    public static void  returnBrokenResource(Jedis jedis){
        jedisPool.returnBrokenResource(jedis);
    }
}
