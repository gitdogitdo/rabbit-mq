package com.mq.rabbit.Controller;

import com.mq.rabbit.mq.service.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class ConcurrencyController {

    private static final Logger log= LoggerFactory.getLogger(ConcurrencyController.class);
    @Autowired
    private InitService initService;
    @RequestMapping(value = "/robbing/thread",method = RequestMethod.GET)
    public String robbingThread() {
        initService.genergateMultiThead();
        return "ok";
    }
}
