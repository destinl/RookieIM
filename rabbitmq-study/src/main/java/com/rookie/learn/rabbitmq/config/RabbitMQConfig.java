package com.rookie.learn.rabbitmq.config;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/118:52
 */
public class RabbitMQConfig {
    /**
     * RabbitMQ的队列主题名称
     */
    public static final String RABBITMQ_DEMO_TOPIC = "rabbitmq.demo.topic";

    /**
     * RabbitMQ的DIRECT交换机名称
     */
    public static final String RABBITMQ_DEMO_DIRECT_EXCHANGE = "rabbitmq.demo.direct.exchange";

    /**
     * RabbitMQ的DIRECT交换机和队列绑定的匹配键 DirectRouting
     */
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "rabbitmq.demo.direct.routing";

    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_A = "fanout.A";

    public static final String FANOUT_EXCHANGE_QUEUE_TOPIC_B = "fanout.B";

    public static final String FANOUT_EXCHANGE_DEMO_NAME = "fanout.exchange.demo.name";
}
