package com.yanghaoyi.user.aop.auth;

import com.yanghaoyi.user.aop.auth.enu.Module;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author : YangHaoYi on 2020/5/13.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/13.
 * Version : V 1.0
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface PermissionModule {
    public Module[] belong() default {Module.ALL};
}
