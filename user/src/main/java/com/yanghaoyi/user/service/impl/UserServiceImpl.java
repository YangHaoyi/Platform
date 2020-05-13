package com.yanghaoyi.user.service.impl;

import com.yanghaoyi.redis.RedisUtil;
import com.yanghaoyi.token.exception.RegisterException;
import com.yanghaoyi.token.exception.TokenException;
import com.yanghaoyi.token.util.JwtUtil;
import com.yanghaoyi.user.dao.UserMapper;
import com.yanghaoyi.user.model.UserEntity;
import com.yanghaoyi.user.mq.producer.RegisterProducer;
import com.yanghaoyi.user.pojo.result.LoginResult;
import com.yanghaoyi.user.pojo.result.VerifyCodeResult;
import com.yanghaoyi.user.service.IUserService;
import com.yanghaoyi.user.service.constants.ErrCodeConstant;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    /** 获取验证码 ***/
    public VerifyCodeResult getVerifyCode(String userName) {
        if (redisUtil.hasKey("register" + userName) && redisUtil.getExpire("register" + userName) > 0) {
            long leftTime = redisUtil.getExpire("register" + userName);
            if(leftTime == 0){
                leftTime = 1;
            }
            throw new RegisterException(ErrCodeConstant.ERROR_VERFY_CODE_TOO_FAST, "操作过快，请秒"+ leftTime +"后再次尝试");
        }else {
            String verifyCode = String.valueOf(new Random(System.currentTimeMillis()).nextInt(899999) + 100000);
            VerifyCodeResult verifyCodeResult = new VerifyCodeResult();
            verifyCodeResult.setUserName(userName);
            verifyCodeResult.setVerifyCode(verifyCode);
            rabbitTemplate.sendVerifyCode(verifyCodeResult);
            return verifyCodeResult;
        }
    }

    @Override
    /** 通过验证码进行注册 ***/
    public UserEntity registerByVerifyCode(String userName, String verifyCode) {
        UserEntity userEntity = new UserEntity();
        if(verifyByRedis(userName,verifyCode)){
            userEntity.setUserName(userName);
            userMapper.insertUser(userEntity);
        }
        return userEntity;
    }

    @Override
    /** 通过验证码进行登录 ***/
    public LoginResult loginByVerifyCode(UserEntity userEntity,String verifyCode) {
        LoginResult loginResult = new LoginResult();
        if(verifyByRedis(userEntity.getUserName(),verifyCode)){
            String token = createToken(userEntity);
            loginResult.setToken(token);
            loginResult.setUserEntity(userEntity);
        }
        return loginResult;
    }

    @Override
    public UserEntity insertUser(String userName,String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPassword(password);
        userMapper.insertUser(userEntity);
        rabbitTemplate.sendMsg(userEntity);
        return userEntity;
    }

    @Override
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userMapper.updateUser(userEntity);
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
    public List<UserEntity> findAllUser() {
        return userMapper.findAllUser();
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


    /** 验证缓存验证码是否正确 ***/
    private boolean verifyByRedis(String userName, String verifyCode) {
        if (redisUtil.hasKey("register" + userName) && redisUtil.getExpire("register" + userName) > 0
                //从Redis中获取用户对应的验证码并进行比对
                &&((VerifyCodeResult)redisUtil.get("register" + userName)).getVerifyCode().equals(verifyCode)) {
            //验证码使用，删除缓存
            redisUtil.del("register" + userName);
            return true;
        }else {
            throw new RegisterException(ErrCodeConstant.ERROR_VERFY_CODE, "验证码错误");
        }
    }

//    启动redis https://www.cnblogs.com/chaojiyingxiong/p/11281502.html
}
