package com.rookie.learn.rabbitmq.producer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.learn.rabbitmq.producer.service.RabbitMQService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/119:08
 */
@RestController
@RequestMapping("/rookie/rabbitmq")
public class RabbitMQController {

    @Resource
    private RabbitMQService rabbitMQService;

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg){
        return rabbitMQService.sendMsg(msg);
    }

    @PostMapping("/fanout")
    public String sendMsgByFanout(@RequestParam(name = "msg") String msg){
        return rabbitMQService.sendMsgByFanoutExchange(msg);
    }

    @PostMapping("/topic")
    public String topicSend(@RequestParam(name = "msg") String msg, @RequestParam(name = "routingKey") String routingKey){
        return rabbitMQService.sendMsgByTopicExchange(msg, routingKey);
    }

    @PostMapping("/header")
    public String headersSend(@RequestParam(name = "msg") String msg,
                              @RequestParam(name = "json") String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        return rabbitMQService.sendMsgByHeadersExchange(msg, map);
    }

    @GetMapping("/test")
    public String test() {
        return "success~555555";
    }
}
