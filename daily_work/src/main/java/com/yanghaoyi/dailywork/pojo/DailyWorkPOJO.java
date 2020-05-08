package com.yanghaoyi.dailywork.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author : YangHaoYi on 2020/4/26.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/26.
 * Version : V 1.0
 */
@Data
public class DailyWorkPOJO {
    /** 主键ID */
    private Integer id;
    /** 创建日期 */
    private long createDate;
    /** 员工Id */
    private int userId;
    /** 员工姓名 */
    private String userName;
    /** 部门 */
    private String department;
    /** 职位 */
    private String position;
    /** 项目类型 */
    private int projectType;
    /** 项目名称 */
    private String project;
    /** 工作日期 */
    private long workDate;
    /** 工时类型  正常0 / 加班 1 */
    private int workType;
    /** 正常工时 */
    private Double workTime;
    /** 加班工时 */
    private Double overTimeWorkTime;
    /** 负责人Id */
    private int masterId;
    /** 负责人 */
    private String masterName;
    /** 工作描述 */
    private String description;

}
