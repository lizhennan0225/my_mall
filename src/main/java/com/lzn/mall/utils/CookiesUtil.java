package com.lzn.mall.utils;

import org.apache.commons.codec.binary.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lzn on 2018/8/30.
 */
public class CookiesUtil {
    private static final String COOKIES_DOMAIN = "localhost";
    private static final String COOKIES_NAME = "mymall_login_token";

    public static void writeLoginToken(HttpServletResponse response, String token){
        Cookie cookie = new Cookie(COOKIES_NAME, token);
        cookie.setDomain(COOKIES_DOMAIN);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*365);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public static String readLoginToken(HttpServletRequest request){
        Cookie [] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (StringUtils.equals(cookie.getName(), COOKIES_NAME)){
                 return cookie.getValue();
            }
        }

        return null;
    }

    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response){
        Cookie [] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (StringUtils.equals(cookie.getName(), COOKIES_NAME)){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

}
