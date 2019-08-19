package com.mq.rabbit.Controller;

import com.mq.rabbit.entity.User;
import com.mq.rabbit.mq.send.Sender1;
import com.mq.rabbit.mq.send.Sender2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {
    @Autowired
    private Sender1 helloSender1;

    @Autowired
    private Sender2 helloSender2;

    @RequestMapping("/hello")
    public String hello() {
        helloSender1.send();
        helloSender2.send();
        return "ok";
    }
    //Sender1:hello1 Wed Jul 17 10:57:24 CST 2019
    //Sender2:hello2 Wed Jul 17 10:57:24 CST 2019
    //Receiver1:hello1 Wed Jul 17 10:57:24 CST 2019
    //Receiver2:hello2 Wed Jul 17 10:57:24 CST 2019
    ///hello的结果为：两个消费者分摊两个生产者的消息。
    // 因为使用的是默认的交换器，一条消息只能被一个队列接收到，
    // 而这两个生产者都监听了这一个队列，因此两个消费者分摊。
    @RequestMapping("/user")
    public String user() {
        User user=new User();
        user.setUserName("a");
        user.setPassword("1");
        user.setSex("m");
        user.setLevel("1");
        helloSender1.sendUser(user);
        helloSender2.sendUser(user);
        return "ok";
    }
    //user的结果为：和 /hello 一样。证明了此处支持对象的发送和接收，只是实体类 必须实现序列化接口。


    @RequestMapping("/topMessage")
    public String topMessage() {
        helloSender1.testTopPicMessage();
        helloSender2.testTopPicMessage();
        return "ok";
    }
    //sendTopPicMessage1:sendTopPicMessage
    //sendTopPicMessages1:sendTopPicMessages
    //sendTopPicMessage2:sendTopPicMessage
    //sendTopPicMessages2:sendTopPicMessages
    //topMessagesReceiver2:sendTopPicMessage
    //topMessageReceiver1:sendTopPicMessage
    //topMessageReceiver1:sendTopPicMessage
    //topMessagesReceiver2:sendTopPicMessages
    //topMessagesReceiver2:sendTopPicMessage
    //topMessagesReceiver2:sendTopPicMessages

    ///topMessage的结果为：
    //        routing_key是“topic.message” 时，exchange绑定的binding_key：“topic.message”，topic.＃都符合路由规则;
    //
    //        所以发送的消息，两个队列都能接收到，而两个消费者各自监听了一个队列，因此消息不会被分摊；
    //
    //        routing_key是“topic.messages”，exchange绑定的binding_key 只有 “topic.＃”符合路由规则;
    //
    //        发送的消息，只有队列topic.messages能收到，只有一个消费者监听了这个队列，因此消息不会被分摊。

    @RequestMapping("/fanoutMessage")
    public String fanoutMessage() {
        helloSender1.testFanoutMessage();
        helloSender2.testFanoutMessage();
        return "ok";
    }

    //fanout Sender1:sendFanoutMessage
    //fanout Sender2:sendFanoutMessage
    //FanoutReceiverB:sendFanoutMessage
    //FanoutReceiverB:sendFanoutMessage
    //FanoutReceiverA:sendFanoutMessage
    //FanoutReceiverA:sendFanoutMessage
    //FanoutReceiverC:sendFanoutMessage
    //FanoutReceiverC:sendFanoutMessage

    // /fanoutMessage的结果为：就算fanoutSender发送消息的时候，指定了routing_key，
    // 但所有绑定的队列都能接受到了消息，而这三个消费者都各自监听了一个队列，因此消息不会被分摊。


    // 总结
    // default exchange 一条消息只能被监听该队列的某一个消费者消费。
    // 队列名称必须和routing_key 一致。
    // topic 交换器：绑定的队列中，只要消息的routing_key满足队列的binding_key的路由规则，该队列即可接收到消息。
    // fanout 交换器：绑定的队列均能收到消息，不会进行路由匹配这一过程。
}
