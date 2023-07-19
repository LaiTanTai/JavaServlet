package repository.Project;

import Entity.ProjectEntity;

import java.sql.Date;
import java.util.List;

public interface ProjectRepositoryImp {
    void updateProject(int id , String name , Date start , Date end );
    List<ProjectEntity> getAllProject();
    void addProject(String name , Date start ,Date end);

    void deleteProject(int id);
}
