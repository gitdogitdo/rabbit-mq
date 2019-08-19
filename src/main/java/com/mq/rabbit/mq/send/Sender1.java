package com.mq.rabbit.mq.send;

import com.mq.rabbit.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
// 发送者
public class Sender1 {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send() {
        String sendMsg = "hello1 " + new Date();
        System.out.println("Sender1:" + sendMsg);
        rabbitTemplate.convertAndSend("helloQueue", sendMsg);
    }

    public void sendUser(User user){
        System.out.println("user Sender1:" + user.getUserName()+"/"+user.getPassword());
        // helloQueue  代表的是queue 的名字  user是消息(实体只需序列化即可)
        // message将会被路由到exchange，然后exchange会将消息路由到正确的queue
        rabbitTemplate.convertAndSend("helloQueue", user);
    }
    public void testTopPicMessage() {
        String msg = "sendTopPicMessage";
        System.out.println("sendTopPicMessage1:" + msg);
        //第一个参数:指定了exchange
        //第二个参数:指定了接受消息的栏目名
        //第三个参数:消息内容
        //到指定exchange找出第二个参数符合的正则表达式,得到对应的Queue,监听相应Queue的消费者接受到消息
        rabbitTemplate.convertAndSend("topicExchange", "topic.Message", msg);//topic.Message、topic.#两个都符合

        msg = "sendTopPicMessages";
        System.out.println("sendTopPicMessages1:" + msg);   //  # 会匹配topicMessages  topicMessage  少一个s 匹配不到   只能多不能少
        rabbitTemplate.convertAndSend("topicExchange", "topic.Messages", msg);//只有topic.#符合
    }


    public void testFanoutMessage(){
        String sendMsg = "sendFanoutMessage";
        System.out.println("fanout Sender1:" + sendMsg);
        //第二个参数不会进行正则表达式的过滤
        //但是必须要填,才能根据exchange找到相关Queue
        rabbitTemplate.convertAndSend("fanoutExchange","", sendMsg);
    }
}
