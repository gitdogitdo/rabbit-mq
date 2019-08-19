package com.mq.rabbit.mq.service;

import com.mq.rabbit.entity.SUser;
import com.mq.rabbit.entity.SysLog;
import com.mq.rabbit.id.IDGenerator;
import com.mq.rabbit.mapper.SUserMapper;
import com.mq.rabbit.mapper.SysLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConcurrencyService {
    private static final Logger log = LoggerFactory.getLogger(ConcurrencyService.class);
    private static final String ProductNo="155bf991d931ebde5f5e5fa31a0b29db";

    @Autowired
    private SysLogMapper productMapper;

    @Autowired
    private SUserMapper productRobbingRecordMapper;

    /**
     * 处理抢单
     * @param mobile
     */
    public Integer manageRobbing(String mobile) {
        Integer insert = 0;
        try {
            // 下单数量
            Integer num = 1;

            /**
             * 抢单请求处理逻辑：
             * 1：有库存 库存 >0
             * 2:扣减库存后，即能更新成功时记录抢单 用户信息
             * 3：异步发送通知给用户抢单成功
             */
            // 查询商品数量
                SysLog sysLog = productMapper.selectById(ProductNo);
                if(sysLog!= null && sysLog.getUserId()>0){
                // 扣减库存
               sysLog.setUserId(sysLog.getUserId()-1);
               log.info("存库剩余量：{}",sysLog.getUserId());
                Integer result = productMapper.updateById(sysLog);
                if(result>0){
                    SUser user = new SUser();
                    user.setId(IDGenerator.MD5.generate());
                    user.setTelephone(mobile);
                    user.setName(sysLog.getId());
                    user.setUsername("order");
                    user.setPassword("123");
                    user.setSalt("123");
                    insert = productRobbingRecordMapper.insert(user);
                    log.info("用户：{}新增:{}",mobile,insert);
                    return  insert;
                }
            }else {
                log.info("库存不足");
            }
        }catch (Exception e){
            log.info("处理抢单发生异常:mobile={}",mobile);
            e.printStackTrace();
        }
        return insert;
    }
}
