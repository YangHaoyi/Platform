package com.yanghaoyi.user.aop;

import com.yanghaoyi.user.model.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author : YangHaoYi on 2020/5/13.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/13.
 * Version : V 1.0
 */
@Log4j2
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.yanghaoyi.user.service.impl.UserServiceImpl.insertUser(..))")
    private void logInsertUser(){

    }

    @Before("logInsertUser()")
    public void beforeInsertUser(JoinPoint joinPoint){
        log.warn("插入数据库开始 ");
    }

    //声明后置通知 ，如果result的类型与proceed执行的方法返回的参数类型不匹配那么就不会执行这个方法
    @AfterReturning(pointcut = "logInsertUser()", returning = "result")
    public void afterInsertUser(UserEntity result){
        log.warn("插入数据库结束 ");
    }


}
