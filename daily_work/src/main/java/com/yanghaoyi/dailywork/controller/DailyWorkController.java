package com.yanghaoyi.dailywork.controller;

import com.yanghaoyi.common.response.Response;
import com.yanghaoyi.common.response.ResponseHelper;
import com.yanghaoyi.dailywork.pojo.DailyWorkPOJO;
import com.yanghaoyi.dailywork.service.IDailyWorkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public Response<Object> insertDailyWork(@RequestBody DailyWorkPOJO dailyWorkPOJO) {
        dailyWorkService.insertDailyWork(dailyWorkPOJO);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "删除日报",notes = "根据Id删除日报",httpMethod = "DELETE")
    @RequestMapping(value = "/dailywork",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> deleteDailyWork(@RequestParam(value = "id")int id){
        dailyWorkService.deleteDailyWorkById(id);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "修改日报",notes = "根据Id修改日报",httpMethod = "PUT")
    @RequestMapping(value = "/dailywork",method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> updateDailyWorkById(@RequestBody DailyWorkPOJO dailyWorkPOJO){
        dailyWorkService.updateDailyWorkById(dailyWorkPOJO);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "查询日报",notes = "根据Id查询日报",httpMethod = "GET")
    @RequestMapping(value = "/dailywork/user",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<List<DailyWorkPOJO>> getDailyWorkById(){
        return ResponseHelper.createSuccessResponse(dailyWorkService.selectDailyWork());
    }

}
