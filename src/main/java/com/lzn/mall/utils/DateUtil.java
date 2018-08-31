package com.lzn.mall.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by lzn on 2018/8/30.
 */
public class DateUtil {
    public static String STANDRND_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String dateTimeStr){

        if (dateTimeStr == null){
            return null;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDRND_FORMAT);
        DateTime dateTime =dateTimeFormatter.parseDateTime(dateTimeStr);

        return dateTime.toDate();
    }

    public static String dateToStr(Date date){
        if (date == null){
            return null;
        }
        DateTime dateTime = new DateTime(date);

        return dateTime.toString();
    }
}
