package com.rookie.im.core.config;

import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2721:18
 */
@Data
public class AppConfig {

    private WebSocketConfig rookie;
    @Data
    public static class WebSocketConfig {
        private Integer port;// tcp 绑定的端口号

        private Integer bossThreadSize; // boss线程 默认=1

        private Integer workThreadSize; //work线程

        private Long heartBeatTime;

        private Integer loginModel;

        private RedisConfig redis;

    }

    @Data
    public static class RedisConfig {
        /**
         * 单机模式：single 哨兵模式：sentinel 集群模式：cluster
         */
        private String mode;
        /**
         * 数据库
         */
        private Integer database;
        /**
         * 密码
         */
        private String password;
        /**
         * 超时时间
         */
        private Integer timeout;
        /**
         * 最小空闲数
         */
        private Integer poolMinIdle;
        /**
         * 连接超时时间(毫秒)
         */
        private Integer poolConnTimeout;
        /**
         * 连接池大小
         */
        private Integer poolSize;

        /**
         * redis单机配置
         */
        private RedisSingle single;
    }

    @Data
    public static class RedisSingle {
        private String address;
    }
}
