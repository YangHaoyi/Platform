package com.yanghaoyi.user.mq.receiver;

import com.yanghaoyi.redis.RedisUtil;
import com.yanghaoyi.user.config.RabbitConfig;
import com.yanghaoyi.user.model.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : YangHaoYi on 2020/4/30.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/30.
 * Version : V 1.0
 */
@Log4j2
@Component
public class SaveToRedisReceiver {

    @Resource
    private RedisUtil redisUtil;

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_USER)
    public void process(UserEntity userEntity) {
        log.warn("插入redis库开始 ");
        redisUtil.set("user"+userEntity.getId(), userEntity, 60);
        log.warn("插入redis库结束 ");
    }
}
