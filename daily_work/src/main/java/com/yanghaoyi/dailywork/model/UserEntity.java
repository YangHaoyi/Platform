package com.yanghaoyi.dailywork.model;

import lombok.Data;

/**
 * @author : YangHaoYi on 2020/4/24.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/24.
 * Version : V 1.0
 */
@Data
public class UserEntity {
    private Integer id;
    private String token;
    private String userName;
    private String password;
}
