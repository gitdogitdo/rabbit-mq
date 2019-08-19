package com.mq.rabbit.mq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class InitService {
    private static final Logger log = LoggerFactory.getLogger(InitService.class);
    public static final int ThreadNum = 11;
    private static int mobile = 0;

    @Autowired
    private CommonMqService commonMqService;

   public void genergateMultiThead(){
    log.info("开始初始化线程数");
       CountDownLatch countDownLatch = new CountDownLatch(1);
       try {
           for(int i=0;i<ThreadNum;i++){
               new Thread(new RunThread(countDownLatch)).start();
           }
           countDownLatch.countDown();
       }catch (Exception e){
            e.printStackTrace();
       }
   }


   private class RunThread implements Runnable{
       private final CountDownLatch startLatch;
       private RunThread(CountDownLatch startLatch) {
           this.startLatch = startLatch;
       }
       @Override
       public void run() {
           try {
               //TODO 线程等待
               startLatch.await();
               mobile+=1;
               // TODO 发送消息入抢单队列：env.getProperty("user.order.queue.name")
               commonMqService.sendRobbingMsgV2(String.valueOf(mobile));
           }catch (Exception e){
                e.printStackTrace();
           }
       }
   }
}
