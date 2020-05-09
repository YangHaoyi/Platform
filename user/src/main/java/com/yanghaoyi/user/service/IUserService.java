package com.yanghaoyi.user.service;

import com.yanghaoyi.user.model.UserEntity;
import com.yanghaoyi.user.pojo.result.LoginResult;
import com.yanghaoyi.user.pojo.result.VerifyCodeResult;

import java.util.List;

/**
 * @author : YangHaoYi on 2020/4/24.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/24.
 * Version : V 1.0
 */
public interface IUserService {

    VerifyCodeResult getVerifyCode(String userName);

    UserEntity registerByVerifyCode(String userName, String verifyCode);

    LoginResult loginByVerifyCode(UserEntity userEntity,String verifyCode);

    UserEntity insertUser(String userName, String password);


    void deleteUser(int userId);

    void updateUser(UserEntity userEntity);

    UserEntity findUserByUserName(String userName);

    UserEntity findUserById(int id);

    List<UserEntity> findAllUser();

    String createToken(UserEntity userEntity);
}
