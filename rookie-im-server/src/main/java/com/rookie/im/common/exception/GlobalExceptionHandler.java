package com.rookie.im.common.exception;

import com.google.protobuf.Api;
import com.rookie.im.common.domain.resp.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1812:11
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    public ApiResult<?> businessExceptionHadnler(BusinessException e){
        log.info("business exception! reason is {}", e.getErrorMsg(), e);
        return ApiResult.fail(e.getErrorCode(), e.getErrorMsg());
    }

}
