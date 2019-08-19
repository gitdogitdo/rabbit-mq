package com.mq.rabbit.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mq.rabbit.entity.SUser;
import com.mq.rabbit.entity.SysLog;
import com.mq.rabbit.id.IDGenerator;
import com.mq.rabbit.service.ISUserService;
import com.mq.rabbit.service.ISysLogService;
import com.mq.rabbit.utils.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jack
 * @since 2019-07-17
 */
@RestController
@RequestMapping("/sUser")
public class SUserController {

    private static final Logger log= LoggerFactory.getLogger(SUserController.class);
    private static final String Prefix="user";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ISUserService userService;
    @Autowired
    private ISysLogService logService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;

   @PostMapping(value = "/login")
    public ResponseMessage login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            //TODO：执行登录逻辑
            Map<String, Object> param = new HashMap<>();
            param.put("username",userName);
            param.put("password",password);
            List<SUser> sUsers = (List<SUser>) userService.selectByMap(param);
            SUser user = sUsers.get(0);
            if (user != null) {
                //TODO：异步写用户日志
                try {
                    SysLog userLog = new SysLog();
                    userLog.setId(IDGenerator.MD5.generate());
                    userLog.setIp("127.0.0.1");
                    userLog.setDescription("登录");
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setExchange(env.getProperty("log.user.exchange.name"));
                    rabbitTemplate.setRoutingKey(env.getProperty("log.user.routing.key.name"));
                    Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
                    message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
                    rabbitTemplate.convertAndSend(message);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //TODO：塞权限数据-资源数据-视野数据
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return response;
        return null;
    }

}

