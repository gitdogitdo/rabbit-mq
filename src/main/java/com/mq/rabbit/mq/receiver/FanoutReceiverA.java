package com.mq.rabbit.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * fanoutA消费者
 */
@Component
@RabbitListener(queues = "fanoutA")
public class FanoutReceiverA {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("FanoutReceiverA:" + msg);
    }

}
