package com.suollon.lvchang.nonbusiness.exceptionhandler;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author wangwl
 * @date 2020/1/15 13:44
 */
public class RespModel<T> implements Serializable {

    /** 错误码. */
    private int code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;

    public static RespModel success() {
        return RespModel.build().code(HttpStatus.OK.value()).msg(HttpStatus.OK.name());
    }

    public static <T> RespModel<T> success(T data) {
        return (RespModel<T>) RespModel.success().data(data);
    }

    /*********************** 链式调用方法 ***********************/
    public RespModel<T> code(int code) {
        this.code = code;
        return this;
    }

    public RespModel<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public RespModel<T> data(T data) {
        this.data = data;
        return this;
    }

    public static <T> RespModel<T> build() {
        return new RespModel<>();
    }

    /*********************** getter,setter ***********************/
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
