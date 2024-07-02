package com.rookie.learn.rabbitmq.consumer.fanout;

import com.rookie.learn.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/210:22
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_A))
public class FanoutExchangeConsumerA {

        @RabbitHandler
        public void process( Map<String, Object> map) {
            System.out.println("收到队列A消息：" + map.toString());
        }
}
