package service.Task;

import Entity.TaskEntity;
import payload.response.Groupwork_details;
import payload.response.UserTask;
import repository.Task.TaskRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TaskService implements TaskServiceImp {
    private TaskRepository taskRepository = new TaskRepository();

    @Override
    public void updateStatus(int id, int status) {
        taskRepository.updateStatus(id,status);
    }

    @Override
    public void addJobUser(int user_id, int project, String tasksname, Date start, Date end, int status) {
        taskRepository.addJobUser(user_id,project,tasksname,start,end,status);
    }
    public void updateTask(int id ,int project , String task , int user_id , Date start , Date end ,int status){
        taskRepository.updateTask(id,project,task,user_id,start,end,status);
    }
    public List<TaskEntity> getAlljob(){
        return taskRepository.getAllTask();
    }

    @Override
    public List<Groupwork_details> getAllTaskProjectById(int id) {
        List<Integer> integers = taskRepository.getAllTaskByProjectID(id);
        List<Groupwork_details> groupwork_details = new ArrayList<>();
        for (Integer item : integers) {
            groupwork_details.add(taskRepository.findTaskStatusByUserId(item,id));
        }
        return groupwork_details;
    }
    public void deleteTask(int id){
        taskRepository.deleteTask(id);
    }
}
