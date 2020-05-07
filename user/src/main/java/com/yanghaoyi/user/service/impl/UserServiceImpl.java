package com.yanghaoyi.user.service.impl;

import com.yanghaoyi.token.exception.TokenException;
import com.yanghaoyi.token.util.JwtUtil;
import com.yanghaoyi.user.dao.UserMapper;
import com.yanghaoyi.user.model.UserEntity;
import com.yanghaoyi.user.mq.producer.RegisterProducer;
import com.yanghaoyi.user.service.IUserService;
import com.yanghaoyi.user.service.constants.ErrCodeConstant;
import com.yanghaoyi.user.util.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : YangHaoYi on 2020/4/24.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/24.
 * Version : V 1.0
 */
@Log4j2
@Service
public class UserServiceImpl implements IUserService, RabbitTemplate.ConfirmCallback {

    @Autowired
    UserMapper userMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RegisterProducer rabbitTemplate;
    /**token失效时间 60 秒*/
    private final long tokenFailureTime = 60*1000;

    @Override
    public UserEntity insertUser(String userName,String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPassword(password);
        log.warn("插入数据库开始 ");
        userMapper.insertUser(userEntity);
        log.warn("插入数据库结束 ");
        rabbitTemplate.sendMsg(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        UserEntity user = findUserById(userEntity.getId());
        return user;
    }

    @Override
    public UserEntity findUserByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }

    @Override
    public UserEntity findUserById(int id) {
        UserEntity userEntity;
        System.out.println("Redis中是否有key： "+ redisUtil.hasKey("user" + id));
        System.out.println("Redis中剩余时间： "+ redisUtil.getExpire("user" + id));
        if (redisUtil.hasKey("user" + id) && redisUtil.getExpire("user" + id) > 0) {
            userEntity = (UserEntity) redisUtil.get("user" + id);
            System.out.println("缓存未过期，从Redis中取");
        }else {
            System.out.println("缓存过期，从数据库中取");
            userEntity = userMapper.findUserById(id);
        }
        return userEntity;
    }

    @Override
    public String createToken(UserEntity userEntity) {
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtUtil.keyUserId,userEntity.getId());
        String token = null;
        try {
            token = JwtUtil.createJWT(claims,"","","",tokenFailureTime);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenException(ErrCodeConstant.ERROR_TOKEN, "Token 错误");
        }
        return token;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            log.warn("消息成功消费");
        } else {
            log.warn("消息消费失败:" + s);
        }

    }

//    启动redis https://www.cnblogs.com/chaojiyingxiong/p/11281502.html
}
