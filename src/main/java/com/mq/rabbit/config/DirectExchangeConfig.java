package com.mq.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    public static final String QUEUE_NAME = "email_queue";

    public static final String EXCHANGE_NAME = "email_exchange";

    @Bean(name = "email_queue")
    public Queue directQuery() {
        return new Queue(QUEUE_NAME);
    }

    @Bean(name = "email_exchange")
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding directBinding(@Qualifier("email_queue") Queue query, @Qualifier("email_exchange") DirectExchange directExchange) {
        return BindingBuilder.bind(query).to(directExchange).with(QUEUE_NAME);
    }
}
