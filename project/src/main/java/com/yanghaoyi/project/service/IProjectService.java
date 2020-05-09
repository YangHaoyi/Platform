package com.yanghaoyi.project.service;

import com.yanghaoyi.project.model.ProjectEntity;
import com.yanghaoyi.project.pojo.parameter.ProjectPageParams;
import com.yanghaoyi.project.pojo.result.PageResult;

import java.util.List;

/**
 * Created by 浩艺 on 2020/5/5.
 */
public interface IProjectService {

    void insertProject(String projectName);

    void deleteProjectById(int projectId);

    void updateProjectNameById(ProjectEntity projectEntity);

    void updateProjectMasterById(int projectId,int masterId,String master);

    List<ProjectEntity> selectProject();

    PageResult selectPageProject(int pageNumber,int pageSize);

    void insertUserProject(int userId,int projectId);

    void deleteUserProjectById(int userId,int projectId);

    List<ProjectEntity> getUserProject(int userId);
}
