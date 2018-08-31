package com.lzn.mall.common;

/**
 * Created by lzn on 2018/8/29.
 */
public enum ResponseCode {

    SUCCESS(0, "SUCCESS"),
    FAILED(-1, "FAILED"),
    NEED_LOGIN(1, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
