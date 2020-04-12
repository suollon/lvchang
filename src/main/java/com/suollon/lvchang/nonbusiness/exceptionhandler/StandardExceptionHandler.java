package com.suollon.lvchang.nonbusiness.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 目前仅区分三种异常，参数校验异常、业务异常、运行时异常
 * @author wangwl
 * @date 2019/12/10 13:45
 */
@RestControllerAdvice
public class StandardExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespModel handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return handleParameterException(e.getBindingResult()).msg("参数校验异常");
    }

    @ExceptionHandler(BindException.class)
    public RespModel handleBindException(BindException e) {
        return handleParameterException(e.getBindingResult()).msg("参数绑定异常");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RespModel handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return RespModel.build().code(HttpStatus.BAD_REQUEST.value()).msg(e.getMessage());
    }

    @ExceptionHandler(StandardException.class)
    public RespModel handleStandardException(StandardException e) {
        return RespModel.build().code(e.getErrorCode()).msg(e.getErrorMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public RespModel handleRuntimeException(RuntimeException e) {
        return RespModel.build().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg(e.getMessage());
    }

    private RespModel handleParameterException(BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for (FieldError error : errors) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return RespModel.build().code(HttpStatus.BAD_REQUEST.value()).data(map);
    }

}
