package com.rookie.learn.rabbitmq.consumer.topic;

import com.rookie.learn.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/215:17
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.TOPIC_EXCHANGE_QUEUE_A))
public class TopicExchangeConsumerA {

    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println("队列[" + RabbitMQConfig.TOPIC_EXCHANGE_QUEUE_A + "]收到消息：" + map.toString());
    }
}
