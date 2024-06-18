package com.rookie.im.common.exception;

import cn.hutool.core.lang.copier.SrcToDestCopier;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommonErrorEnum implements ErrorEnum{
    PARAM_INVALID(-2, "参数校验失败{0}")
    ;

    private Integer errCode;

    private String errMsg;

    @Override
    public Integer getErrorCode() {
        return errCode;
    }

    @Override
    public String getErrorMsg() {
        return errMsg;
    }
}
