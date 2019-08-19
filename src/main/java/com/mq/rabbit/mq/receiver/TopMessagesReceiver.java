package com.mq.rabbit.mq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;

@Component
@RabbitListener(queues = "topicMessages")
public class TopMessagesReceiver {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("topMessagesReceiver2:" +msg);
    }

    // sendTopPicMessages  只有topic.# 可以用 只有 topicMessages  可以监听到  但是有两个发送者
    // 发送的消息，只有队列topic.messages能收到，只有一个消费者监听了这个队列，因此消息不会被分摊。
}
