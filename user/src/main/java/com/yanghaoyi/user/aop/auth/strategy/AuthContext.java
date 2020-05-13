package com.yanghaoyi.user.aop.auth.strategy;

import com.yanghaoyi.user.aop.auth.enu.Module;

/**
 * @author : YangHaoYi on 2020/5/13.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/13.
 * Version : V 1.0
 */
public class AuthContext {

    private AuthStrategy authStrategy;

    public AuthContext(AuthStrategy strategy) {
        this.authStrategy=strategy;
    }


    /**
     * 执行策略
     * @param modules
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean execute(Module[] modules, int userId)  {
        return this.authStrategy.executeAuth(modules,userId);
    }

}
