package com.lzn.mall.common;

/**
 * Created by lzn on 2018/8/30.
 */
public class Const {
    public interface Session {
        int SESSION_EXPIRE_TIME = 60 * 30;
    }

    public interface USER_ROLE{
        int ADMIN = 1;
        int USER = 0;
    }
}
