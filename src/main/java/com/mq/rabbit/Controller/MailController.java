package com.mq.rabbit.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    private static final Logger log = LoggerFactory.getLogger(MailController.class);
    private static final String Prefix="mail";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;

    @GetMapping(value = "/send")
    public String sendMail(){

        try{
            rabbitTemplate.setExchange(env.getProperty("mail.exchange.naem"));
            rabbitTemplate.setRoutingKey(env.getProperty("mail.exchangge.key.name"));
            rabbitTemplate.convertAndSend(MessageBuilder.withBody("mail发送".getBytes("UTF-8")).build());
        }catch (Exception e){
            log.info("发送邮件出错");
            e.printStackTrace();
        }

        return "ok";
    }
}
