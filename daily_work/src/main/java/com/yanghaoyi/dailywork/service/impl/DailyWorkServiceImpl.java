package com.yanghaoyi.dailywork.service.impl;

import com.yanghaoyi.dailywork.dao.DailyWorkMapper;
import com.yanghaoyi.dailywork.model.DailyWorkEntity;
import com.yanghaoyi.dailywork.pojo.DailyWorkPOJO;
import com.yanghaoyi.dailywork.service.IDailyWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : YangHaoYi on  2020/5/7.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on  2020/5/7.
 * Version : V 1.0
 */
@Service
public class DailyWorkServiceImpl implements IDailyWorkService {

    @Autowired
    DailyWorkMapper dailyWorkMapper;

    @Override
    public void insertDailyWork(DailyWorkPOJO dailyWorkPOJO) {
        DailyWorkEntity dailyWorkEntity = new DailyWorkEntity();
        dailyWorkEntity.setCreateDate(convertDate(dailyWorkPOJO.getCreateDate()));
        dailyWorkEntity.setUserId(dailyWorkPOJO.getUserId());
        dailyWorkEntity.setUserName(dailyWorkPOJO.getUserName());
        dailyWorkEntity.setDepartment(dailyWorkPOJO.getDepartment());
        dailyWorkEntity.setPosition(dailyWorkPOJO.getPosition());
        dailyWorkEntity.setProjectType(dailyWorkPOJO.getProjectType());
        dailyWorkEntity.setProject(dailyWorkPOJO.getProject());
        dailyWorkEntity.setWorkDate(convertDate(dailyWorkPOJO.getWorkDate()));
        dailyWorkEntity.setWorkTime(dailyWorkPOJO.getWorkTime());
        dailyWorkEntity.setOverTimeWorkTime(dailyWorkPOJO.getOverTimeWorkTime());
        dailyWorkEntity.setMasterId(dailyWorkPOJO.getMasterId());
        dailyWorkEntity.setMasterName(dailyWorkPOJO.getMasterName());
        dailyWorkEntity.setDescription(dailyWorkPOJO.getDescription());
        dailyWorkMapper.insertDailyWork(dailyWorkEntity);
    }

    private Date convertDate(long times){
        Date date = new Date(times);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

}
