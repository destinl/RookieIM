package com.rookie.learn.rabbitmq.producer.config;


import com.rookie.learn.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/118:53
 */
@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue rabbitmqDemoDirectQueue(){
        return new Queue(RabbitMQConfig.RABBITMQ_DEMO_TOPIC, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqDemoDirectExchange(){
        return new DirectExchange(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindDirect(){
        return BindingBuilder
                .bind(rabbitmqDemoDirectQueue())
                .to(rabbitmqDemoDirectExchange())
                .with(RabbitMQConfig.RABBITMQ_DEMO_DIRECT_ROUTING);
    }

}
