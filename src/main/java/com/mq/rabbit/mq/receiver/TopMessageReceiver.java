package com.mq.rabbit.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * topicMessage消费者
 */
@Component
@RabbitListener(queues = "topicMessage")
public class TopMessageReceiver {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("topMessageReceiver1:" +msg);
    }
}
