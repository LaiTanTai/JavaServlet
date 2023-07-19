package service.Project;

import Entity.ProjectEntity;

import java.sql.Date;
import java.util.List;

public interface ProjectServiceImp {
    void updateProject(int id , String name , Date start, Date end);
    List<ProjectEntity> getAllProjectService();

    void addProject(String name, Date start, Date end);

    void deleteProject(int id);
}
