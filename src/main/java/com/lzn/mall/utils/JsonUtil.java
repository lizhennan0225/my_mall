package com.lzn.mall.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lzn.mall.pojo.User;
import com.mysql.jdbc.log.Log;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Created by lzn on 2018/8/30.
 */
public class JsonUtil<T> {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转timestamp形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空bean转Json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setDateFormat(new SimpleDateFormat(DateUtil.STANDRND_FORMAT));
        /*以上是序列化配置，以下是反序列化配置*/
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("对象转json字符串失败", e);
            return null;
        }
    }

    public static <T> String obj2PrettyString(T obj) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            logger.error("json字符串转对象失败", e);
            return null;
        }
    }

    /**
     * json字符串转复杂对象
     * @param str
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        try {
            return (T) (typeReference.equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            logger.error("json字符串转对象失败", e);
            return null;
        }
    }

    public static <T> T string2Obj(String str, Class<?> collectionClass, Class<?> ... elementClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);

        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            logger.error("json字符串转对象失败", e);
            return null;
        }
    }
}
