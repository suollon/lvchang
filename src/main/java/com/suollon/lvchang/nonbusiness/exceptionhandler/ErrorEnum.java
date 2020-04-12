package com.suollon.lvchang.nonbusiness.exceptionhandler;

/**
 * @author wangwl
 * @date 2019/12/10 13:43
 */
public enum ErrorEnum {

    /*********************** 通用 ***********************/
    E4001000(4001000, "{}"),
    E4001001(4001001, "未登录"),

    /*********************** 用户服务 ***********************/
    E4002000(4002000, "用户不存在"),

    /*********************** 活动服务 ***********************/
    E4003000(4003000, "活动已下架"),

    /*********************** 奖品服务 ***********************/
    E4004000(4004000, "奖品异常"),

    /*********************** ..服务 ***********************/
    E4005000(4005000, "..异常"),

    E4006000(-1, "限流控制，请稍后重试~"),
    ;

    private int code;
    private String message;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
