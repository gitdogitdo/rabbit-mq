package com.mq.rabbit.mq.receiver;

import com.mq.rabbit.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloQueue")
// 监听者
public class HelloReceiver1 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1:" + hello);
    }

    @RabbitHandler
    public void processUser(User user) {
        System.out.println("user receive1:" + user.getUserName()+"/"+user.getPassword());
    }
}
