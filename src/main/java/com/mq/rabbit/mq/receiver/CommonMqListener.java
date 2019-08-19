package com.mq.rabbit.mq.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mq.rabbit.entity.SysLog;
import com.mq.rabbit.mapper.SysLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CommonMqListener {

    private static final Logger log= LoggerFactory.getLogger(CommonMqListener.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SysLogMapper userLogMapper;


/**
     * 监听消费用户日志
     * @param message
     */

    @RabbitListener(queues = "${log.user.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeUserLogQueue(@Payload byte[] message){
        try {
            SysLog userLog=objectMapper.readValue(message, SysLog.class);
            log.info("监听消费用户日志 监听到消息： {} ",userLog);
            //TODO：记录消息入数据表
            userLogMapper.insert(userLog);
            // 如果报错 则放入新的队列
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //发送邮件的场景，其实也是比较常见的，比如用户注册需要邮箱验证，
    // 用户异地登录发送邮件通知等等，在这里我以 RabbitMQ 实现异步发送邮件。实现的步骤跟场景一几乎一致！ 一对一
    @RabbitListener(queues = "${mail.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMailQueue(@Payload byte[] message){

        log.info("发送邮件成功");
    }

}
