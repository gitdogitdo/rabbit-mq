package com.mq.rabbit.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * fanoutC消费者
 */
@Component
@RabbitListener(queues = "fanoutC")
public class FanoutReceiverC {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("FanoutReceiverC:" + msg);
    }

}
