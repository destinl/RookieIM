package com.rookie.im.core.server.utils.reids;

import com.rookie.im.core.config.AppConfig;
import lombok.Getter;
import org.redisson.api.RedissonClient;

import java.util.SplittableRandom;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2721:55
 */
public class RedisManager {

    @Getter
    private static RedissonClient redissonClient;

    private static final Object lock = new Object();

    public static void init(AppConfig appConfig) {
        if(redissonClient == null){
            synchronized (lock){
                if(redissonClient == null){
                    SingleClientStrategy singleClientStrategy = new SingleClientStrategy();
                    redissonClient = singleClientStrategy.getRedissonClient(appConfig.getRookie().getRedis());
                }
            }
        }
    }

    public static RedissonClient getRedissonClient(){
        if(redissonClient == null){
            throw new IllegalStateException("RedissonClient is not initialized yet. Please call init() first. ");
        }
        return redissonClient;
    }
}
