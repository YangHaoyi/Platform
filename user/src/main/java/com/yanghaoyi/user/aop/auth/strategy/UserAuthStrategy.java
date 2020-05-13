package com.yanghaoyi.user.aop.auth.strategy;

import com.yanghaoyi.user.aop.auth.enu.Module;
import com.yanghaoyi.user.dao.UserMapper;
import com.yanghaoyi.user.model.UserEntity;
import com.yanghaoyi.user.service.IUserService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : YangHaoYi on 2020/5/13.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/13.
 * Version : V 1.0
 */
@Component
public class UserAuthStrategy implements AuthStrategy{

    @Resource
    private IUserService userService;

    @Override
    public boolean executeAuth(Module[] modules, int userId) {
        if(ArrayUtils.contains(modules, Module.ALL)) {
            return true;
        }
        List<Integer> moduleList=new ArrayList<Integer>();
        for(Module module:modules) {
            moduleList.add(module.ordinal());
        }
        UserEntity userEntity =userService.findUserById(userId);
        return moduleList.contains(userEntity.getMaster());
    }
}
