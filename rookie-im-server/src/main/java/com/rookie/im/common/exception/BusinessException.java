package com.rookie.im.common.exception;

import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1812:14
 */
@Data
public class BusinessException extends RuntimeException{

    private Integer errorCode;

    private String errorMsg;

    public BusinessException(){
        super();
    }

    public BusinessException(Integer errorCode, String errMsg){
        super(errMsg);
        this.errorCode = errorCode;
        this.errorMsg = errMsg;
    }

    public BusinessException(ErrorEnum error){
        super(error.getErrorMsg());
        this.errorMsg = error.getErrorMsg();
        this.errorCode = error.getErrorCode();
    }
}
