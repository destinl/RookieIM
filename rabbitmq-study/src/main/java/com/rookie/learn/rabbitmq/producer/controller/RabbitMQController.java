package com.rookie.learn.rabbitmq.producer.controller;

import com.rookie.learn.rabbitmq.producer.service.RabbitMQService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
