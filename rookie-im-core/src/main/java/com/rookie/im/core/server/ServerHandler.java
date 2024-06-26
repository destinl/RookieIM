package com.rookie.im.core.server;

import com.rookie.im.core.codec.proto.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2621:52
 */
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
//        ctx.writeAndFlush(msg.toString());
        System.out.println(msg.toString());
    }

}
