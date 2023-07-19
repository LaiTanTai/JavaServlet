package service.Project;

import Entity.ProjectEntity;
import repository.Project.ProjectRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProjectService implements ProjectServiceImp {
    private ProjectRepository projectRepository = new ProjectRepository();

    @Override
    public void updateProject(int id, String name, Date start, Date end) {
        projectRepository.updateProject(id ,name , start,end);
    }

    @Override
    public List<ProjectEntity> getAllProjectService() {
        return projectRepository.getAllProject();
    }

    public void addProject(String name, Date start, Date end) {
        projectRepository.addProject(name,start,end);
    }

    public void deleteProject(int id) {
        projectRepository.deleteProject(id);
    }
}
