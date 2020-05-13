package com.yanghaoyi.user.aop.auth.strategy;

import com.yanghaoyi.user.aop.auth.enu.Module;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author : YangHaoYi on 2020/5/13.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/13.
 * Version : V 1.0
 */
public class MasterAuthStrategy implements AuthStrategy{

    @Override
    public boolean executeAuth(Module[] modules, int userId){
        if(ArrayUtils.contains(modules, Module.ALL)) {
            return true;
        }

        return false;
    }
}
