package com.mq.rabbit.mq.receiver;

import com.mq.rabbit.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * @Description:
 * helloQueue消费者2
 */
@Component
@RabbitListener(queues = "helloQueue")
public class HelloReceiver2 {
    @RabbitHandler
    public void process(String mesg) {
        System.out.println("Receiver2:" + mesg);
    }

    @RabbitHandler
    public void processUser(User user) {
        System.out.println("user receive2:" + user.getUserName()+"/"+user.getPassword());
    }
}
