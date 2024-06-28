package com.rookie.im.core.server.domain.model;

import lombok.Data;


/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2820:31
 */
@Data
public class UserSession {
    private Integer appId;

    private Integer clientType;

    private String userId;

    private String imei;

    private String version;

    private Integer connectState;

    private Integer brokerHost;
}
