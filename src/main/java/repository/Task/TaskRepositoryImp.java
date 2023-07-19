package repository.Task;

import Entity.TaskEntity;
import payload.response.Groupwork_details;
import payload.response.UserTask;
import payload.response.GroupDetailsStatus;

import java.sql.Date;
import java.util.List;

public interface TaskRepositoryImp {
    void updateStatus(int id , int status);
    List<UserTask> findTaskByUserId(int id);
    void addJobUser(int user_id , int project , String tasksname , Date start, Date end, int status);
    List<TaskEntity> getAllTask();
    Groupwork_details findTaskStatusByUserId(int user_id,int project_id);
    List<Integer> getAllTaskByProjectID(int id);
    void deleteTask(int id);
    void updateTask(int id, int project , String task , int user_id , Date start, Date end,int status);
}
