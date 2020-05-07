package com.yanghaoyi.user.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : YangHaoYi on 2020/4/24.
 * Email  :  yang.haoyi@qq.com
 * Description :项目类
 * Change : YangHaoYi on 2020/4/24.
 * Version : V 1.0
 */
@Data
public class ProjectEntity implements Serializable{
    /** 主键ID */
    private Integer id;
    /** 项目名称 */
    private String name;
    /** 外键 */
    private Integer hid;
}
