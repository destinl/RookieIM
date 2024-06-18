package com.rookie.im.common.exception;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.google.protobuf.Api;
import com.rookie.im.common.domain.resp.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.rookie.im.common.exception.CommonErrorEnum.PARAM_INVALID;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1812:11
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        StringBuilder errMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors()
                .forEach(x -> errMsg.append(x.getField()).append(x.getDefaultMessage()).append(","));
        String message = errMsg.toString();
        return ApiResult.fail(PARAM_INVALID.getErrCode(), message.substring(0, message.length()-1));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    public ApiResult<?> businessExceptionHadnler(BusinessException e){
        log.info("business exception! reason is {}", e.getErrorMsg(), e);
        return ApiResult.fail(e.getErrorCode(), e.getErrorMsg());
    }

}
