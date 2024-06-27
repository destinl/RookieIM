package com.rookie.im.core.server.domain.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2714:03
 */
@Data
public class UserClientDto {
    private Integer appId;

    private Integer clientType;

    private String userId;

    private String imei;
}
