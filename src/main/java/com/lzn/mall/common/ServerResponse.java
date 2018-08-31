package com.lzn.mall.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * Created by lzn on 2018/8/29.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable{
    private String msg;
    private int status;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ServerResponse(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }

    public ServerResponse(T data, int status) {
        this.data = data;
        this.status = status;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static <T> ServerResponse<T> createBySuccessData(T data){
        return new ServerResponse<T>(data, ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createByErrorMsg(String msg){
        return new ServerResponse<T>(msg, ResponseCode.FAILED.getCode());
    }

    public static <T> ServerResponse<T> createByErrorMsg(String msg, int code){
        return new ServerResponse<T>(msg, code);
    }


}
