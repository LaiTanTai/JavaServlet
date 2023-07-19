package service.Task;

import Entity.TaskEntity;
import payload.response.Groupwork_details;

import java.sql.Date;
import java.util.List;

public interface TaskServiceImp {
    void updateStatus(int id ,int status);
    void addJobUser(int user_id , int project, String tasksname, Date start,Date end,int status);
    List<TaskEntity> getAlljob();
    List<Groupwork_details> getAllTaskProjectById(int id);
    void deleteTask(int id);
    void updateTask(int id , int project , String task ,int user_id, Date start,Date end,int status);
}
