package com.yanghaoyi.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanghaoyi.project.dao.ProjectMapper;
import com.yanghaoyi.project.model.ProjectEntity;
import com.yanghaoyi.project.model.UserProjectEntity;
import com.yanghaoyi.project.pojo.parameter.ProjectPageParams;
import com.yanghaoyi.project.pojo.result.PageResult;
import com.yanghaoyi.project.service.IProjectService;
import com.yanghaoyi.project.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 浩艺 on 2020/5/5.
 */
@Service
public class ProjectServiceImpl implements IProjectService{

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void insertProject(String projectName) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(projectName);
        projectMapper.insertProject(projectEntity);
    }

    @Override
    public void deleteProjectById(int projectId) {
        projectMapper.deleteProjectById(projectId);
    }

    @Override
    public void updateProjectNameById(ProjectEntity projectEntity) {
        projectMapper.updateProjectNameById(projectEntity);
    }

    @Override
    public void updateProjectMasterById(int projectId,int masterId,String master) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(projectId);
        projectEntity.setMasterId(masterId);
        projectEntity.setMaster(master);
        projectMapper.updateProjectMasterById(projectEntity);
    }

    @Override
    public List<ProjectEntity> selectProject() {
        return projectMapper.selectProject();
    }

    @Override
    public PageResult selectPageProject(int pageNumber,int pageSize) {
        ProjectPageParams pageParams = new ProjectPageParams();
        pageParams.setPageNum(pageNumber);
        pageParams.setPageSize(pageSize);
        return PageUtil.getPageResult(pageParams, getProjectPageInfo(pageParams));
    }

    @Override
    public void insertUserProject(int userId, int projectId) {
        UserProjectEntity userProjectEntity = new UserProjectEntity();
        userProjectEntity.setUid(userId);
        userProjectEntity.setPid(projectId);
        projectMapper.insertUserProject(userProjectEntity);
    }

    @Override
    public void deleteUserProjectById(int userId, int projectId) {
        UserProjectEntity userProjectEntity = new UserProjectEntity();
        userProjectEntity.setUid(userId);
        userProjectEntity.setPid(projectId);
        projectMapper.deleteUserProjectById(userProjectEntity);
    }

    @Override
    public List<ProjectEntity> getUserProject(int userId) {
        return projectMapper.getUserProject(userId);
    }


    private PageInfo<ProjectEntity> getProjectPageInfo(ProjectPageParams projectPageParams){
        int pageNum = projectPageParams.getPageNum();
        int pageSize = projectPageParams.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectEntity> projectEntityList = projectMapper.selectProject();
        return new PageInfo<ProjectEntity>(projectEntityList);
    }
}
