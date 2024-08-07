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

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机名称
     */
    public static final String TOPIC_EXCHANGE_DEMO_NAME = "topic.exchange.demo.name";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列A的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_A = "topic.queue.a";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列B的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_B = "topic.queue.b";

    /**
     * RabbitMQ的TOPIC_EXCHANGE交换机的队列C的名称
     */
    public static final String TOPIC_EXCHANGE_QUEUE_C = "topic.queue.c";

    /**
     * HEADERS_EXCHANGE交换机名称
     */
    public static final String HEADERS_EXCHANGE_DEMO_NAME = "headers.exchange.demo.name";

    /**
     * RabbitMQ的HEADERS_EXCHANGE交换机的队列A的名称
     */
    public static final String HEADERS_EXCHANGE_QUEUE_A = "headers.queue.a";

    /**
     * RabbitMQ的HEADERS_EXCHANGE交换机的队列B的名称
     */
    public static final String HEADERS_EXCHANGE_QUEUE_B = "headers.queue.b";
}
