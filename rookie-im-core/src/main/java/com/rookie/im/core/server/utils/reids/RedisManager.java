package com.rookie.im.core.server.utils.reids;

import com.rookie.im.core.config.AppConfig;
import org.redisson.api.RedissonClient;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2721:55
 */
public class RedisManager {

    private static RedissonClient redissonClient;

    public static void init(AppConfig appConfig) {
        SingleClientStrategy singleClientStrategy = new SingleClientStrategy();
        redissonClient = singleClientStrategy.getRedissonClient(appConfig.getRookie().getRedis());
    }
}
