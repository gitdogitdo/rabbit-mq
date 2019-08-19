package com.mq.rabbit.mq.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mq.rabbit.mq.service.ConcurrencyService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userOrderListener")
public class UserOrderListener implements ChannelAwareMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(UserOrderListener.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ConcurrencyService concurrencyService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            // 得到的消息 处理抢单逻辑
            byte[] body = message.getBody();
            String mobile = new String(body, "UTF-8");
            logger.info("监听到的抢单的手机号{}",mobile);
            Integer integer = concurrencyService.manageRobbing(mobile);
            if(integer>0){
                /****   抢单结束*****/
                // 确认消息已消费
                channel.basicAck(tag,true);
            }else {
                // 处理异常  消息未消费  手动确认
                channel.basicAck(tag,false);
            }

        }catch (Exception e){
            logger.error("用户抢单异常：",e.fillInStackTrace());
            channel.basicAck(tag,false);
        }
    }
}
