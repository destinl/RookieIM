package com.rookie.im.core.mq.publish;

import com.rookie.im.common.constants.Constants;
import com.rookie.im.common.enums.CommandType;
import com.rookie.im.core.codec.proto.Message;
import com.rabbitmq.client.Channel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rookie.im.core.mq.factory.MqFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/216:51
 */
@Slf4j
public class MqMessageProducer {

    public static void sendMessage(Message message, Integer command){
        Channel channel = null;
        String channelName = "";
        CommandType commandType = CommandType.getCommandType(command.toString());
        switch (commandType){
            case SYSTEM:
                channelName = Constants.RabbitConstants.Im2MessageService;
            case USER:
                channelName = Constants.RabbitConstants.Im2UserService;
            case GROUP:
                channelName = Constants.RabbitConstants.Im2GroupService;
            default:
                channelName = Constants.RabbitConstants.Im2MessageService;
        }

        try {
            channel = MqFactory.getChannel(channelName);
            JSONObject o = (JSONObject) JSON.toJSON(message.getMessagePack());
            o.put("command", command);
            o.put("clientType", message.getMessageHeader().getClientType());
            o.put("imei",message.getMessageHeader().getImei());
            o.put("appId", message.getMessageHeader().getAppId());
            channel.basicPublish(channelName, "", null, o.toJSONString().getBytes());
        } catch (IOException e) {
            log.error("mq消息推送出现异常：{}", e.getMessage());
        }
    }
}
