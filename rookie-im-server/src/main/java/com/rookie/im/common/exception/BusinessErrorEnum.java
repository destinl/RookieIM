package com.rookie.im.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BusinessErrorEnum implements ErrorEnum{

    BUSINESS_ERROR(1001, "{0}"),
    SYSTEM_ERROE(1002, "系统开小差了")
    ;

    private Integer code;

    private String msg;

    @Override
    public Integer getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }
}
