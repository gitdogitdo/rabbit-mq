package com.mq.rabbit.mq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CommonMqService {

    private static final Logger log = LoggerFactory.getLogger(CommonMqService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;
    // 发送消息到抢单队列
    public void sendRobbingMsgV2(String mobile) {
        try {
            rabbitTemplate.setExchange(env.getProperty("user.order.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("user.order.routing.key.name"));
            Message message = MessageBuilder.withBody(mobile.getBytes()).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
            rabbitTemplate.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
