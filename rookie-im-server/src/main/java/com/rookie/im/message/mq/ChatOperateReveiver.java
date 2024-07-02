package com.rookie.im.message.mq;
import com.rookie.im.common.constants.Constants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;
import com.rabbitmq.client.Channel;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/217:17
 */
@Component
@Slf4j
public class ChatOperateReveiver {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = Constants.RabbitConstants.Im2MessageService, durable = "true"),
                    exchange = @Exchange(value = Constants.RabbitConstants.Im2MessageService, durable = "true")
            ), concurrency = "1"
    )
    public void onChatMessage(@Payload Message message,
                              @Headers Map<String, Object> headers,
                              Channel channel) throws UnsupportedEncodingException {
        String msg = new String(message.getBody(), "utf-8");
        log.info("CHAT MSG FROM QUEUE ::: {}", msg);
    }
}
