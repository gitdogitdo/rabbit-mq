package com.mq.rabbit.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "fanoutB")
public class FanoutReceiverB {


    @RabbitHandler
    public void process(String msg) {
        System.out.println("FanoutReceiverB:" + msg);
    }

}
