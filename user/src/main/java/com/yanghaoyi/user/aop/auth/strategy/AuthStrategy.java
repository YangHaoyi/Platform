package com.yanghaoyi.user.aop.auth.strategy;

import com.yanghaoyi.user.aop.auth.enu.Module;

/**
 * @author : YangHaoYi on 2020/5/13.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/13.
 * Version : V 1.0
 */
public interface AuthStrategy {
    public boolean executeAuth(Module[] modules, int userId);
}
