package com.rookie.learn.rabbitmq.producer.service;

import com.rookie.learn.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/119:01
 */
@Service
public class RabbitMQService {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    private RabbitTemplate rabbitTemplate;

    public String sendMsg(String msg){
        rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE,
                RabbitMQConfig.RABBITMQ_DEMO_DIRECT_ROUTING, getMessage(msg));
        return "ok";
    }

    public String sendMsgByFanoutExchange(String msg){
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_DEMO_NAME,
                "", getMessage(msg));
        return "ok";
    }

    private Map<String, Object> getMessage(String msg){
        String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        String sendTime =sdf.format(new Date());
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", sendTime);
        map.put("msg", msg);

        return map;
    }
}
