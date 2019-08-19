package com.mq.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
    }

    @Bean
    public Queue helloQueue() {
        return new Queue("helloQueue");
    }

    @Bean
    public Queue topicMessage() {
        return new Queue("topicMessage");
    }

    @Bean
    public Queue topicMessages() {
        return new Queue("topicMessages");
    }

    // 新增声明三个Queue
    @Bean
    public Queue fanoutA() {
        return new Queue("fanoutA");
    }

    @Bean
    public Queue fanoutB() {
        return new Queue("fanoutB");
    }

    @Bean
    public Queue fanoutC() {
        return new Queue("fanoutC");
    }

    /***************************************exchange***********************************************/
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /***************************************将队列和exchange绑定***********************************************/


//  BindingKey 指定当前Exchange下，什么样的RoutingKey会被下派到当前绑定的Queue中

    /**
     * 将队列topicMessage与topicExchange绑定，
     * 只有栏目名为topic.Message才能匹配，
     * 得到当前的Queue
     *
     * @param topicMessage
     * @param topicExchange
     * @returns
     */
    @Bean
    Binding bindingExchangeMessage(Queue topicMessage, TopicExchange topicExchange) {
        //topic.Message、topic.#两个都符合，因此两个消费
        return BindingBuilder.bind(topicMessage).to(topicExchange).with("topic.Message");
    }

    /**
     * 将队列topicMessages与topicExchange绑定，
     * 以topic	开头的栏目名均会模糊匹配,
     * 得到当前的Queue
     *
     * @param topicMessages
     * @param topicExchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue topicMessages, TopicExchange topicExchange) {
        //只有topic.#符合，只有topMessages符合接受消息的条件

        //“#”通配任何零个或多个word  匹配topicMessages
        //“*”通配任何单个word
        return BindingBuilder.bind(topicMessages).to(topicExchange).with("topic.#");  // 只有队列 topic.messages 能收到
    }

    /**
     * 将队列fanoutA与fanoutExchange绑定
     *
     * @param fanoutA
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue fanoutA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutA).to(fanoutExchange);
    }

    /**
     * 将队列fanoutA与fanoutExchange绑定
     *
     * @param fanoutB
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeB(Queue fanoutB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutB).to(fanoutExchange);
    }

    /**
     * 将队列fanoutA与fanoutExchange绑定
     *
     * @param fanoutC
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeC(Queue fanoutC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutC).to(fanoutExchange);
    }

}
