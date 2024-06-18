package com.rookie.im.common.domain.resp;

import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/1811:40
 */
@Data
public class ApiResult<T> {
    private Boolean success;

    private Integer errCode;

    private String errMsg;

    private T data;

    public static <T> ApiResult<T> success(){
        ApiResult<T> result = new ApiResult<>();
        result.setData(null);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> ApiResult<T> success(T data){
        ApiResult<T> result = new ApiResult<>();
        result.setData(data);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static <T> ApiResult<T> fail(Integer code, String msg){
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(Boolean.FALSE);
        result.setErrCode(code);
        result.setErrMsg(msg);
        return result;
    }
}

