package com.yanghaoyi.user.service;

import com.yanghaoyi.user.model.UserEntity;

/**
 * @author : YangHaoYi on 2020/4/24.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/24.
 * Version : V 1.0
 */
public interface IUserService {

    UserEntity insertUser(String userName, String password);

    UserEntity updateUser(UserEntity userEntity);

    UserEntity findUserByUserName(String userName);

    UserEntity findUserById(int id);

    String createToken(UserEntity userEntity);
}
