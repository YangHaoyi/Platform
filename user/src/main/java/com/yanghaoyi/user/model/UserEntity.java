package com.yanghaoyi.user.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author : YangHaoYi on 2020/4/24.
 * Email  :  yang.haoyi@qq.com
 * Description :用户类
 * Change : YangHaoYi on 2020/4/24.
 * Version : V 1.0
 */
@Data
public class UserEntity implements Serializable {
    /** 主键ID */
    private Integer id;
    /** 用户Token */
    private String token;
    /** 登录名（工作邮箱） */
    private String userName;
    /** 登录密码 */
    private String password;
    /** 员工姓名 */
    private String name;
    /** 员工工号 */
    private String idNumber;
    /** 部门 */
    private String department;
    /** 职位 */
    private String position;
}
