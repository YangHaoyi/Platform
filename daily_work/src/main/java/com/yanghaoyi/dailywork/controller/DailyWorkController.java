package com.yanghaoyi.dailywork.controller;

import com.yanghaoyi.dailywork.pojo.DailyWorkPOJO;
import com.yanghaoyi.dailywork.service.IDailyWorkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : YangHaoYi on 2020/4/26.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/26.
 * Version : V 1.0
 */
@RestController
@RequestMapping(value = "avi/v1/work")
public class DailyWorkController {

    @Resource
    private IDailyWorkService dailyWorkService;

    @ApiOperation(value = "插入日报", notes = "插入日报信息", httpMethod = "POST")
    @RequestMapping(value = "/dailywork", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void insertDateTest(@RequestBody DailyWorkPOJO dailyWorkPOJO) {
        dailyWorkService.insertDailyWork(dailyWorkPOJO);
    }

}
