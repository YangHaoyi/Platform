package com.yanghaoyi.project.controller;

import com.yanghaoyi.common.response.Response;
import com.yanghaoyi.common.response.ResponseHelper;
import com.yanghaoyi.project.model.ProjectEntity;
import com.yanghaoyi.project.service.IProjectService;
import com.yanghaoyi.project.service.UserFeignClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 浩艺 on 2020/5/4.
 * https://blog.csdn.net/Dream_Weave/article/details/85172796?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1
 */
@RestController
@RequestMapping(value = "api/v1/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;
    @Autowired
    private UserFeignClient userFeignClient;

    @ApiOperation(value = "添加项目",notes = "新建工作项目",httpMethod = "POST")
    @RequestMapping(value = "/project",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> insertProject(@RequestParam(value="projectName") String projectName){
        projectService.insertProject(projectName);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "删除项目",notes = "删除项目",httpMethod = "DELETE")
    @RequestMapping(value = "/project",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> deleteProject(@RequestParam(value = "projectId") int projectId){
        projectService.deleteProjectById(projectId);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "修改项目名称",notes = "根据项目Id修改项目名称",httpMethod = "PATCH")
    @RequestMapping(value = "/project",method = RequestMethod.PATCH,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> updateProject(@RequestBody ProjectEntity projectEntity){
        projectService.updateProjectNameById(projectEntity);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "修改项目所属负责人",notes = "根据项目Id修改项目所属负责人",httpMethod = "PATCH")
    @RequestMapping(value = "/master",method = RequestMethod.PATCH,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> updateProjectMaster(@RequestParam(value = "id") int id,@RequestParam(value = "masterId") int masterId,@RequestParam(value = "master") String master){
        projectService.updateProjectMasterById(id,masterId,master);
        return ResponseHelper.createSuccessResponse();
    }


    @ApiOperation(value = "查询项目",notes = "查询所有项目",httpMethod = "GET")
    @RequestMapping(value = "/project",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<List<ProjectEntity>> getProjectList(){
        return ResponseHelper.createSuccessResponse(projectService.selectProject());
    }

    @ApiOperation(value = "分页查询项目",notes = "分页查询项目",httpMethod = "GET")
    @RequestMapping(value = "/project/page",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> getProjectList(@RequestParam(value = "pageNum")int pageNum,@RequestParam(value = "pageSize") int pageSize){
        return ResponseHelper.createSuccessResponse(projectService.selectPageProject(pageNum,pageSize));
    }


    @ApiOperation(value = "添加用户所属项目",notes = "添加用户所属项目",httpMethod = "POST")
    @RequestMapping(value = "/userProject",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> insertUserProject(@RequestParam(value="userId")int userId,@RequestParam(value="projectId")int projectId){
        projectService.insertUserProject(userId,projectId);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "删除用户所属项目",notes = "删除用户所属项目",httpMethod = "DELETE")
    @RequestMapping(value = "/userProject",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> deleteUserProject(@RequestParam(value="userId")int userId,@RequestParam(value="projectId")int projectId){
        projectService.deleteUserProjectById(userId,projectId);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "查询用户所属项目",notes = "根据用户Id查询用户所属项目",httpMethod = "GET")
    @RequestMapping(value = "/userProject",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<List<ProjectEntity>> getUserProject(){
        //通过USER微服务获取用户ID
        Response<Integer> userIdEntity = userFeignClient.getUserId();
        if (userIdEntity.getCode() == 0) {
            //使用用户ID进行关联表查询
            List<ProjectEntity> projectEntityList = projectService.getUserProject(userIdEntity.getPayload());
            return ResponseHelper.createSuccessResponse(projectEntityList);
        }else {
            return ResponseHelper.createResponse(10001,"获取用户Id异常");
        }
    }


}
