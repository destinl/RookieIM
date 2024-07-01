package com.rookie.learn.rabbitmq.consumer.direct;

import com.rookie.learn.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/120:20
 */
@Component
@RabbitListener(queues = RabbitMQConfig.RABBITMQ_DEMO_TOPIC)
public class ConsumerService {

    @RabbitHandler
    public void process( Map<String, Object> map) {
        System.out.println("收到消息：" + map.toString());
//        System.out.println(1);
    }

}
