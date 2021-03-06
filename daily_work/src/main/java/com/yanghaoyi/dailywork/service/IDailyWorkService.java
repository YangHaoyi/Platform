package com.yanghaoyi.dailywork.service;

import com.yanghaoyi.dailywork.model.DailyWorkEntity;
import com.yanghaoyi.dailywork.pojo.DailyWorkPOJO;

import java.util.List;

/**
 * @author : YangHaoYi on  2020/5/7.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on  2020/5/7.
 * Version : V 1.0
 */
public interface IDailyWorkService {

    void insertDailyWork(DailyWorkPOJO dailyWorkPOJO);

    void deleteDailyWorkById(int id);

    void updateDailyWorkById(DailyWorkPOJO dailyWorkEntity);

    List<DailyWorkPOJO> selectDailyWork();
}
