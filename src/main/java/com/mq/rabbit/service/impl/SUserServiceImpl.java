package com.mq.rabbit.service.impl;

import com.mq.rabbit.entity.SUser;
import com.mq.rabbit.mapper.SUserMapper;
import com.mq.rabbit.service.ISUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jack
 * @since 2019-07-17
 */
@Service
public class SUserServiceImpl extends ServiceImpl<SUserMapper, SUser> implements ISUserService {

}
