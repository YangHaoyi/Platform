package com.yanghaoyi.project.dao;

import com.yanghaoyi.project.model.ProjectEntity;
import com.yanghaoyi.project.model.UserProjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 浩艺 on 2020/5/4.
 */
@Mapper
public interface ProjectMapper {

    int insertProject(ProjectEntity projectEntity);

    void deleteProjectById(int projectId);

    void updateProjectNameById(ProjectEntity projectEntity);

    void updateProjectMasterById(ProjectEntity projectEntity);

    List<ProjectEntity> selectProject();

    List<ProjectEntity> selectProjectByPage();

    int insertUserProject(UserProjectEntity userProjectEntity);

    int deleteUserProjectById(UserProjectEntity userProjectEntity);

    List<ProjectEntity> getUserProject(int userId);

    ProjectEntity findProjectById(int id);

    ProjectEntity findProjectByUserId(int id);
}
