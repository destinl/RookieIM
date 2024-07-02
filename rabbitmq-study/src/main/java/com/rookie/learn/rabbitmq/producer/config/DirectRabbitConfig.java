package com.rookie.learn.rabbitmq.producer.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import com.rookie.learn.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/118:53
 */
@Configuration
@Component
public class DirectRabbitConfig implements BeanPostProcessor {

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin( connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public Queue fanoutExchangeQueueA(){
        return new Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_A, true, false, false);
    }

    @Bean
    public Queue fanoutExchangeQueueB(){
        return new Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_B, true, false, false);
    }

    @Bean
    public FanoutExchange rabbitmqDemoFanoutExchange(){
        return new FanoutExchange(RabbitMQConfig.FANOUT_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Binding bindFanoutA(){
        return BindingBuilder
                .bind(fanoutExchangeQueueA())
                .to(rabbitmqDemoFanoutExchange());
    }

    @Bean
    public Binding bindFanoutB(){
        return BindingBuilder
                .bind(fanoutExchangeQueueB())
                .to(rabbitmqDemoFanoutExchange());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof RabbitAdmin){
            RabbitAdmin rabbitAdmin = (RabbitAdmin) bean;
            rabbitAdmin.declareExchange(rabbitmqDemoFanoutExchange());
            rabbitAdmin.declareQueue(fanoutExchangeQueueA());
            rabbitAdmin.declareQueue(fanoutExchangeQueueB());
        }
        return bean;
    }

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
