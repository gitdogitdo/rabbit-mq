package com.mq.rabbit.config;

import com.mq.rabbit.mq.receiver.UserOrderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitMQConfig {

    private static final Logger log= LoggerFactory.getLogger(RabbitConfig.class);
    @Autowired
    private Environment env;
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    @Autowired
    private UserOrderListener userOrderListener;

    @Bean
    public SimpleMessageListenerContainer listenerContainer(@Qualifier("userOrderQueue") Queue userOrderQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());
        // TODO 并发配置
        // 当前消费者
        container.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency", Integer.class));
        // 最大并发量
        container.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency", Integer.class));
        //
        container.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",Integer.class));
        // TODO 消息确认机制
        container.setQueues(userOrderQueue);
        container.setMessageListener(userOrderListener);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }
}
