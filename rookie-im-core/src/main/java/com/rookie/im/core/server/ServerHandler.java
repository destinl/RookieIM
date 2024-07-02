package com.rookie.im.core.server;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rookie.im.common.enums.command.SystemCommand;
import com.rookie.im.core.codec.pack.LoginPack;
import com.rookie.im.core.codec.proto.Message;
import com.rookie.im.core.mq.publish.MqMessageProducer;
import com.rookie.im.core.server.utils.SessionSocketHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import static com.rookie.im.common.constants.Constants.*;


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
            handleLogin(ctx, msg);
        } else if(command == SystemCommand.LOGOUT.getCommand()){
            handleLogout(ctx);
        } else {
            handleMessage(msg, command);
        }
    }

    private void handleMessage(Message msg, Integer command) {
        MqMessageProducer.sendMessage(msg, command);
    }

    private void handleLogout(ChannelHandlerContext ctx) {
        SessionSocketHolder.removeUserSession((NioSocketChannel) ctx.channel());
    }

    private void handleLogin(ChannelHandlerContext ctx, Message msg) {
        LoginPack loginPack = JSONObject.parseObject(JSONObject.toJSONString(msg.getMessagePack()),
                new TypeReference<LoginPack>(){}.getType());
        setChannelAttributes(ctx, msg, loginPack);
        //保存用户会话
        SessionSocketHolder.createUserSession(ctx,msg,loginPack);
    }

    private void setChannelAttributes(ChannelHandlerContext ctx, Message msg, LoginPack loginPack) {
        ctx.channel().attr(AttributeKey.valueOf(UserId)).set(loginPack.getUserId());
        ctx.channel().attr(AttributeKey.valueOf(AppId)).set(msg.getMessageHeader().getAppId());
        ctx.channel().attr(AttributeKey.valueOf(ClientType)).set(msg.getMessageHeader().getClientType());
        ctx.channel().attr(AttributeKey.valueOf(IMEI)).set(msg.getMessageHeader().getImei());

    }

}
