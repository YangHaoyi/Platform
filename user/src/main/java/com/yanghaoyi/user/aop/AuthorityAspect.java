package com.yanghaoyi.user.aop;

import com.yanghaoyi.token.util.TokenUtil;
import com.yanghaoyi.user.dao.UserMapper;
import com.yanghaoyi.user.exception.AuthorityException;
import com.yanghaoyi.user.model.UserEntity;
import com.yanghaoyi.user.service.constants.ErrCodeConstant;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : YangHaoYi on 2020/5/13.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/13.
 * Version : V 1.0
 */
@Aspect
@Component
@Log4j2
public class AuthorityAspect {

//    @Autowired
//    UserMapper userMapper;
//
//
//    @Pointcut("execution(* com.yanghaoyi.user.service.impl.UserServiceImpl.deleteUser(..))")
//    private void authDeleteUser(){
//
//    }
//
//    @Around("authDeleteUser()")
//    public void beforeInsertUser(ProceedingJoinPoint pjp){
//            int userId = TokenUtil.getTokenUserId();
//            UserEntity userEntity =userMapper.findUserById(userId);
//            log.warn("用户信息为  : "+ userEntity.toString());
//            if (userEntity.getMaster() == 1) {
//                try {
//                    pjp.proceed();
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//            }else {
//                throw new AuthorityException(ErrCodeConstant.ERROR_AUTH, "没有权限");
//            }
//    }

}
