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

import java.util.HashMap;
import java.util.Map;

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
    public TopicExchange rabbitmqDemoTopicExchange() {
        //配置TopicExchange交换机
        return new TopicExchange(RabbitMQConfig.TOPIC_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Queue topicExchangeQueueA() {
        //创建队列1
        return new Queue(RabbitMQConfig.TOPIC_EXCHANGE_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue topicExchangeQueueB() {
        //创建队列2
        return new Queue(RabbitMQConfig.TOPIC_EXCHANGE_QUEUE_B, true, false, false);
    }

    @Bean
    public Queue topicExchangeQueueC() {
        //创建队列3
        return new Queue(RabbitMQConfig.TOPIC_EXCHANGE_QUEUE_C, true, false, false);
    }

    @Bean
    public Binding bindTopicA() {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(topicExchangeQueueA())
                .to(rabbitmqDemoTopicExchange())
                .with("a.*");
    }

    @Bean
    public Binding bindTopicB() {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(topicExchangeQueueB())
                .to(rabbitmqDemoTopicExchange())
                .with("a.*");
    }

    @Bean
    public Binding bindTopicC() {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(topicExchangeQueueC())
                .to(rabbitmqDemoTopicExchange())
                .with("rabbit.#");
    }

    @Bean
    public Queue headersQueueA() {
        return new Queue(RabbitMQConfig.HEADERS_EXCHANGE_QUEUE_A, true, false, false);
    }

    @Bean
    public Queue headersQueueB() {
        return new Queue(RabbitMQConfig.HEADERS_EXCHANGE_QUEUE_B, true, false, false);
    }

    @Bean
    public HeadersExchange rabbitmqDemoHeadersExchange() {
        return new HeadersExchange(RabbitMQConfig.HEADERS_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Binding bindHeadersA(){
        Map<String, Object> map = new HashMap<>();
        map.put("key_one", "java");
        map.put("key_two", "rabbit");
        return BindingBuilder.bind(headersQueueA())
                .to(rabbitmqDemoHeadersExchange())
                .whereAll(map).match();
    }

    @Bean
    public Binding bindHeadersB() {
        Map<String, Object> map = new HashMap<>();
        map.put("headers_A", "rookie");
        map.put("headers_B", "im");
        //部分匹配
        return BindingBuilder.bind(headersQueueB())
                .to(rabbitmqDemoHeadersExchange())
                .whereAny(map).match();
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

            rabbitAdmin.declareExchange(rabbitmqDemoTopicExchange());
            rabbitAdmin.declareQueue(topicExchangeQueueA());
            rabbitAdmin.declareQueue(topicExchangeQueueB());
            rabbitAdmin.declareQueue(topicExchangeQueueC());

            rabbitAdmin.declareExchange(rabbitmqDemoHeadersExchange());
            rabbitAdmin.declareQueue(headersQueueA());
            rabbitAdmin.declareQueue(headersQueueB());
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
