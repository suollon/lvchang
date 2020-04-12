package com.suollon.lvchang.nonbusiness.exceptionhandler;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author wangwl
 * @date 2019/12/10 13:39
 */
public class StandardException extends RuntimeException {

    private int errorCode;
    private String errorMessage;

    public StandardException(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getCode();
        this.errorMessage = errorEnum.getMessage();
    }

    public StandardException(ErrorEnum errorEnum, String... formats) {
        this.errorCode = errorEnum.getCode();
        this.errorMessage = format(errorEnum.getMessage(), formats);
    }

    private static String format(String message, String... formats) {
        if (!ArrayUtils.isEmpty(formats)) {
            for (String f : formats) {
                message = message.replaceFirst("\\{\\}", f);
            }
        }
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
