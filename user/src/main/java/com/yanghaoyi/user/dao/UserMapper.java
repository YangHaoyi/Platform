package com.yanghaoyi.user.dao;


import com.yanghaoyi.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : YangHaoYi on 2020/4/24.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/24.
 * Version : V 1.0
 */
@Mapper
public interface UserMapper {

    int insertUser(UserEntity userEntity);

    void deleteUser(int userId);

    void updateUser(UserEntity userEntity);

    UserEntity findUserByUserName(String userName);

    UserEntity findUserById(int id);

    List<UserEntity> findAllUser();

}
