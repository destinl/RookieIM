package com.rookie.im.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserExceptionEnum implements ErrorEnum{

    OUT_BOUND_IMPORT_LIMIT(-1, "超出用户导入数量上限，请分批导入")
    ;

    private Integer code;

    private String msg;

    @Override
    public Integer getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}
