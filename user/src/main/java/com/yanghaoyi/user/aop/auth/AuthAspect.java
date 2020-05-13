package com.yanghaoyi.user.aop.auth;

import com.yanghaoyi.token.util.TokenUtil;
import com.yanghaoyi.user.aop.auth.enu.Module;
import com.yanghaoyi.user.aop.auth.strategy.AuthContext;
import com.yanghaoyi.user.aop.auth.strategy.AuthStrategy;
import com.yanghaoyi.user.aop.auth.strategy.UserAuthStrategy;
import com.yanghaoyi.user.exception.AuthorityException;
import com.yanghaoyi.user.service.constants.ErrCodeConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author : YangHaoYi on 2020/5/13.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/13.
 * Version : V 1.0
 */
@Aspect
@Component
public class AuthAspect {

    @Autowired
    private UserAuthStrategy userAuthStrategy;

    @Around("execution(* com.yanghaoyi.user.controller..*(..))")
    public Object executeAround(ProceedingJoinPoint jp) throws Throwable{
        Object  obj =null;
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Method realMethod = jp.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());
        if(TokenUtil.getRequest().getRequestURI().contains("/login")||
                TokenUtil.getRequest().getRequestURI().contains("/register")||
                TokenUtil.getRequest().getRequestURI().contains("/verifyCode")){
            //用户登录/注册不进行获取userId处理，直接执行
            obj =  jp.proceed();
        }else {
            int userId = TokenUtil.getTokenUserId();
            if(isHasPermission(realMethod,userId)) {
                //用户拥有该方法权限时执行方法里面的内容
                obj =  jp.proceed();
            }else {
                throw new AuthorityException(ErrCodeConstant.ERROR_AUTH, "没有权限");
            }
        }
        return obj;
    }


    private boolean isHasPermission(Method realMethod, int userId) {
        if(realMethod.isAnnotationPresent(PermissionModule.class)) {
            PermissionModule permissionModule=realMethod.getAnnotation(PermissionModule.class);
            Module[] modules= permissionModule.belong();
            //执行权限策略，判断用户权限
            return new AuthContext(userAuthStrategy).execute(modules,userId);
        }
        return true;
    }

}
