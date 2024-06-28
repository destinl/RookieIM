package com.rookie.im.core.server;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rookie.im.common.enums.ImConnectStatusEnum;
import com.rookie.im.common.enums.command.SystemCommand;
import com.rookie.im.core.codec.pack.LoginPack;
import com.rookie.im.core.codec.proto.Message;
import com.rookie.im.core.server.domain.model.UserSession;
import com.rookie.im.core.server.utils.SessionSocketHolder;
import com.rookie.im.core.server.utils.reids.RedisManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import static com.rookie.im.core.server.constants.Constants.*;


/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2621:52
 */
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        Integer command = msg.getMessageHeader().getCommand();
        if(command == SystemCommand.LOGIN.getCommand()){
            LoginPack loginPack = JSONObject.parseObject(JSONObject.toJSONString(msg.getMessagePack()),
                    new TypeReference<LoginPack>(){}.getType());

            ctx.channel().attr(AttributeKey.valueOf(UserId)).set(loginPack.getUserId());
            ctx.channel().attr(AttributeKey.valueOf(AppId)).set(msg.getMessageHeader().getAppId());
            ctx.channel().attr(AttributeKey.valueOf(ClientType)).set(msg.getMessageHeader().getClientType());
            ctx.channel().attr(AttributeKey.valueOf(IMEI)).set(msg.getMessageHeader().getImei());

            UserSession userSession = new UserSession();
            userSession.setAppId(msg.getMessageHeader().getAppId());
            userSession.setClientType(msg.getMessageHeader().getClientType());
            userSession.setUserId((loginPack.getUserId()));
            userSession.setConnectState(ImConnectStatusEnum.ONLINE_STATUS.getCode());
            userSession.setImei(msg.getMessageHeader().getImei());


            RedissonClient redissonClient = RedisManager.getRedissonClient();
            RMap<String, String> map =
                    redissonClient.getMap(msg.getMessageHeader().getAppId() + RedisConstants.UserSessionConstants + loginPack.getUserId());
            map.put(msg.getMessageHeader().getClientType() + ":" + msg.getMessageHeader().getImei(),
                    JSONObject.toJSONString(userSession));

            SessionSocketHolder.put(msg.getMessageHeader().getAppId(),
                    loginPack.getUserId(),
                    msg.getMessageHeader().getClientType(),
                    msg.getMessageHeader().getImei(),
                    (NioSocketChannel) ctx.channel());
        } else if(command == SystemCommand.LOGOUT.getCommand()){
            String userId = (String) ctx.channel().attr(AttributeKey.valueOf(UserId)).get();
            Integer appId = (Integer) ctx.channel().attr(AttributeKey.valueOf(AppId)).get();
            Integer clientType = (Integer) ctx.channel().attr(AttributeKey.valueOf(ClientType)).get();
            String imei = (String) ctx.channel().attr(AttributeKey.valueOf(IMEI)).get();

            SessionSocketHolder.remove(appId, userId, clientType, imei);

            RedissonClient redissonClient = RedisManager.getRedissonClient();
            RMap<Object, Object> map =
                    redissonClient.getMap(appId + RedisConstants.UserSessionConstants + userId);
            map.remove(clientType + ":" + imei);
            ctx.channel().close();
        }
    }

}
