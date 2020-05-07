package com.yanghaoyi.dailywork.dao;

import com.yanghaoyi.dailywork.model.DailyWorkEntity;
import com.yanghaoyi.dailywork.model.DateTest;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : YangHaoYi on 2020/5/7.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/7.
 * Version : V 1.0
 */
@Mapper
public interface DailyWorkMapper {

    void insertDailyWork(DailyWorkEntity dailyWorkEntity);
}
