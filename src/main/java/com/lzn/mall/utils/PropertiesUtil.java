package com.lzn.mall.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by lzn on 2018/8/29.
 */
public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    static{
        String propsFileName = "mall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(propsFileName), "UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件mall.properties加载失败", e);
        }
    }

    public static String getProperty(String key, String defaultValue) {
        if (!StringUtils.isBlank(key)){
            Object value = props.get(key);
            if (null != value){
                return value.toString().trim();
            }
        }

        return defaultValue.trim();
    }

    public static String getProperty(String key) {
        if (!StringUtils.isBlank(key)){
            Object value = props.get(key);
            if (null != value){
                return value.toString();
            }
        }

        return null;
    }

}
