package com.yanghaoyi.dailywork.dao;

import com.yanghaoyi.dailywork.model.DailyWorkEntity;
import com.yanghaoyi.dailywork.model.DateTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    void deleteDailyWorkById(int id);

    void updateDailyWorkById(DailyWorkEntity dailyWorkEntity);

    List<DailyWorkEntity> selectDailyWork();
}
