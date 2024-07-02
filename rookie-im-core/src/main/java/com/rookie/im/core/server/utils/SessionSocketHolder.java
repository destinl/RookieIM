package com.rookie.im.core.server.utils;

import com.alibaba.fastjson.JSONObject;
import com.rookie.im.common.enums.ImConnectStatusEnum;
import com.rookie.im.core.codec.pack.LoginPack;
import com.rookie.im.core.codec.proto.Message;
import com.rookie.im.common.constants.Constants;
import com.rookie.im.core.server.domain.dto.UserClientDto;
import com.rookie.im.core.server.domain.model.UserSession;
import com.rookie.im.core.server.utils.reids.RedisManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.rookie.im.common.constants.Constants.*;
import static com.rookie.im.common.constants.Constants.IMEI;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2714:04
 */
public class SessionSocketHolder {

    private static final Map<UserClientDto, NioSocketChannel> CHANNELS = new ConcurrentHashMap<>();

    public static void put(Integer appId, String userId,Integer clientType, String imei,  NioSocketChannel channel) {
        UserClientDto userClientDto = new UserClientDto();
        userClientDto.setAppId(appId);
        userClientDto.setUserId(userId);
        userClientDto.setClientType(clientType);
        userClientDto.setImei(imei);
        CHANNELS.put(userClientDto, channel);
    }

    public static void remove(Integer appId, String userId, Integer clientType, String imei) {
        UserClientDto userClientDto = new UserClientDto();
        userClientDto.setAppId(appId);
        userClientDto.setUserId(userId);
        userClientDto.setClientType(clientType);
        userClientDto.setImei(imei);
        CHANNELS.remove(userClientDto);
    }

    public static void createUserSession(ChannelHandlerContext ctx, Message msg, LoginPack loginPack){
        UserSession userSession = new UserSession();
        userSession.setAppId(msg.getMessageHeader().getAppId());
        userSession.setClientType(msg.getMessageHeader().getClientType());
        userSession.setUserId((loginPack.getUserId()));
        userSession.setConnectState(ImConnectStatusEnum.ONLINE_STATUS.getCode());
        userSession.setImei(msg.getMessageHeader().getImei());


        RedissonClient redissonClient = RedisManager.getRedissonClient();
        RMap<String, String> map =
                redissonClient.getMap(msg.getMessageHeader().getAppId() + Constants.RedisConstants.UserSessionConstants + loginPack.getUserId());
        map.put(msg.getMessageHeader().getClientType() + ":" + msg.getMessageHeader().getImei(),
                JSONObject.toJSONString(userSession));

        SessionSocketHolder.put(msg.getMessageHeader().getAppId(),
                loginPack.getUserId(),
                msg.getMessageHeader().getClientType(),
                msg.getMessageHeader().getImei(),
                (NioSocketChannel) ctx.channel());
    }

    public static void removeUserSession(NioSocketChannel nioSocketChannel){
        String userId = (String) nioSocketChannel.attr(AttributeKey.valueOf(UserId)).get();
        Integer appId = (Integer) nioSocketChannel.attr(AttributeKey.valueOf(AppId)).get();
        Integer clientType = (Integer) nioSocketChannel.attr(AttributeKey.valueOf(ClientType)).get();
        String imei = (String) nioSocketChannel.attr(AttributeKey.valueOf(IMEI)).get();

        SessionSocketHolder.remove(appId, userId, clientType, imei);

        RedissonClient redissonClient = RedisManager.getRedissonClient();
        RMap<Object, Object> map =
                redissonClient.getMap(appId + RedisConstants.UserSessionConstants + userId);
        map.remove(clientType + ":" + imei);
        nioSocketChannel.close();
    }
}
