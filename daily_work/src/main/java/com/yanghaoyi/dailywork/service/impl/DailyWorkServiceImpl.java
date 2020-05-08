package com.yanghaoyi.dailywork.service.impl;

import com.yanghaoyi.dailywork.dao.DailyWorkMapper;
import com.yanghaoyi.dailywork.model.DailyWorkEntity;
import com.yanghaoyi.dailywork.pojo.DailyWorkPOJO;
import com.yanghaoyi.dailywork.service.IDailyWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        dailyWorkMapper.insertDailyWork(dailyWorkPOJOToEntity(dailyWorkPOJO));
    }

    @Override
    public void deleteDailyWorkById(int id) {
        dailyWorkMapper.deleteDailyWorkById(id);
    }

    @Override
    public void updateDailyWorkById(DailyWorkPOJO dailyWorkPOJO) {
        dailyWorkMapper.updateDailyWorkById(dailyWorkPOJOToEntity(dailyWorkPOJO));
    }

    @Override
    public List<DailyWorkPOJO> selectDailyWork() {
        List<DailyWorkEntity> dailyWorkEntityList = dailyWorkMapper.selectDailyWork();
        List<DailyWorkPOJO> dailyWorkPOJOList = new ArrayList<>();
        for(DailyWorkEntity dailyWorkEntity:dailyWorkEntityList){
            dailyWorkPOJOList.add(dailyWorkEntityToDailyWorkPOJO(dailyWorkEntity));
        }
        return dailyWorkPOJOList;
    }

    private Date convertDate(long times){
        if(times != 0){
            Date date = new Date(times);
            return date;
        }else {
            return null;
        }
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = formatter.format(date);
//        ParsePosition pos = new ParsePosition(8);
//        Date currentTime_2 = formatter.parse(dateString, pos);
    }

    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }


    private DailyWorkEntity dailyWorkPOJOToEntity(DailyWorkPOJO dailyWorkPOJO){
        DailyWorkEntity dailyWorkEntity = new DailyWorkEntity();
        dailyWorkEntity.setId(dailyWorkPOJO.getId());
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
        return dailyWorkEntity;
    }

    private DailyWorkPOJO dailyWorkEntityToDailyWorkPOJO(DailyWorkEntity dailyWorkEntity){
        DailyWorkPOJO dailyWorkPOJO = new DailyWorkPOJO();
        dailyWorkPOJO.setId(dailyWorkEntity.getId());
        dailyWorkPOJO.setCreateDate(dailyWorkEntity.getCreateDate().getTime());
        dailyWorkPOJO.setUserId(dailyWorkEntity.getUserId());
        dailyWorkPOJO.setUserName(dailyWorkEntity.getUserName());
        dailyWorkPOJO.setDepartment(dailyWorkEntity.getDepartment());
        dailyWorkPOJO.setPosition(dailyWorkEntity.getPosition());
        dailyWorkPOJO.setProjectType(dailyWorkEntity.getProjectType());
        dailyWorkPOJO.setProject(dailyWorkEntity.getProject());
        dailyWorkPOJO.setWorkDate(dailyWorkEntity.getWorkDate().getTime());
        dailyWorkPOJO.setWorkTime(dailyWorkEntity.getWorkTime());
        dailyWorkPOJO.setOverTimeWorkTime(dailyWorkEntity.getOverTimeWorkTime());
        dailyWorkPOJO.setMasterId(dailyWorkEntity.getMasterId());
        dailyWorkPOJO.setMasterName(dailyWorkEntity.getMasterName());
        dailyWorkPOJO.setDescription(dailyWorkEntity.getDescription());
        return dailyWorkPOJO;
    }
}
