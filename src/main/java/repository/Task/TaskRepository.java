package repository.Task;

import Entity.TaskEntity;
import config.Mysqlconfig;
import payload.response.Groupwork_details;
import payload.response.UserTask;
import payload.response.GroupDetailsStatus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements TaskRepositoryImp{
    @Override
    public void addJobUser(int user_id, int project, String tasksname, Date start, Date end, int status) {
        Connection connection = null;
        try{
            String sql = "insert into tasks(name,start_date,end_date,user_id,job_id,status_id) values(?,?,?,?,?,?)";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setString(1,tasksname);
            statement.setDate(2,start);
            statement.setDate(3,end);
            statement.setInt(4,user_id);
            statement.setInt(5,project);
            statement.setInt(6,status);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.print("cant create data addJobUser"+e.getMessage());
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection addJobUser"+e.getMessage());
                }
            }
        }
    }

    @Override
    public void updateStatus(int id, int status) {
        Connection connection = null;
        try{
            String sql = "update tasks set status_id = ? where tasks.id=?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,status);
            statement.setInt(2,id);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.print("cant update data"+e.getMessage());
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection addJobUser"+e.getMessage());
                }
            }
        }
    }

    public List<UserTask> findTaskByUserId(int id){
        Connection connection = null;
        List<UserTask> userTaskList = new ArrayList<>();
        try{
            String sql = "select tasks.id task_id ,tasks.name task_name ,tasks.start_date start , tasks.end_date end, users.fullname" +
                    " user_name,status.name status_name ,jobs.name job_name from tasks join users on tasks.user_id = users.id and users.id = ? join jobs on tasks.job_id = jobs.id " +
                    "join status on tasks.status_id=status.id";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                UserTask userTask = new UserTask();
                userTask.setId(resultSet.getInt("task_id"));
                userTask.setName(resultSet.getString("task_name"));
                userTask.setUsername(resultSet.getString("user_name"));
                userTask.setJob_name(resultSet.getString("job_name"));
                userTask.setStatus(resultSet.getString("status_name"));
                userTask.setStart(resultSet.getDate("start"));
                userTask.setEnd(resultSet.getDate("end"));
                userTaskList.add(userTask);
            }
        }catch(Exception e){
            System.out.print("cant get data getAlljob"+e.getMessage());
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection addJobUser"+e.getMessage());
                }
            }
        }
        return userTaskList;
    }
    public Groupwork_details findTaskStatusByUserId(int user_id,int project_id){
        Connection connection = null;
        List<UserTask> userTaskList = new ArrayList<>();
        Groupwork_details Groupdetail = new Groupwork_details();
        try{
            String sql = "select tasks.id task_id ,tasks.name task_name ,tasks.start_date start , tasks.end_date end, users.fullname user_name,status.name status_name ,jobs.name job_name from tasks join users on tasks.user_id = users.id and users.id = ? join jobs on tasks.job_id = jobs.id and jobs.id =? join status on tasks.status_id=status.id";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,user_id);
            statement.setInt(2,project_id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                UserTask userTask = new UserTask();
                userTask.setId(resultSet.getInt("task_id"));
                userTask.setName(resultSet.getString("task_name"));
                userTask.setUsername(resultSet.getString("user_name"));
                Groupdetail.setName(resultSet.getString("user_name"));
                userTask.setJob_name(resultSet.getString("job_name"));
                userTask.setStatus(resultSet.getString("status_name"));
                if(userTask.getStatus().equals("Chưa thực hiện")){
                    Groupdetail.setNotDone(Groupdetail.getNotDone()+1);
                }else if(userTask.getStatus().equals("Đang thực hiện")){
                    Groupdetail.setInprocess(Groupdetail.getInprocess()+1);
                }else{
                    Groupdetail.setDone(Groupdetail.getDone()+1);
                }
                userTask.setStart(resultSet.getDate("start"));
                userTask.setEnd(resultSet.getDate("end"));
                userTaskList.add(userTask);
            }
            Groupdetail.setTaskList(userTaskList);
        }catch(Exception e){
            System.out.print("cant get data getAlljob"+e.getMessage());
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection addJobUser"+e.getMessage());
                }
            }
        }
        return Groupdetail ;
    }
    public void updateTask(int id , int project , String task , int user_id , Date start , Date end , int status){
        Connection connection = null;
        try{
            String sql = "update tasks set name=?,start_date=?,end_date=?,user_id=?,job_id=?,status_id=? where id=?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setString(1,task);
            statement.setDate(2,start);
            statement.setDate(3,end);
            statement.setInt(4,user_id);
            statement.setInt(5,project);
            statement.setInt(6,status);
            statement.setInt(7,id);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.print("cant update task"+e.getMessage());
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection"+e.getMessage());
                }
            }
        }
    }
    public void deleteTask(int id){
        Connection connection = null;
        try{
            String sql = "delete from tasks where tasks.id = ?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.print("cant delete data "+e.getMessage());
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection "+e.getMessage());
                }
            }
        }
    }
    public List<TaskEntity> getAllTask(){
        Connection connection = null;
        List<TaskEntity> taskEntities= new ArrayList<>();
        try{
            String sql = "select tasks.id task_id ,tasks.name task_name ,tasks.start_date start , tasks.end_date end, users.fullname" +
                    " user_name,status.name status_name ,jobs.name job_name from tasks join users on tasks.user_id = users.id join jobs on tasks.job_id = jobs.id " +
                    "join status on tasks.status_id=status.id";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                TaskEntity taskEntity = new TaskEntity();
                taskEntity.setId(resultSet.getInt("task_id"));
                taskEntity.setName(resultSet.getString("task_name"));
                taskEntity.setUsername(resultSet.getString("user_name"));
                taskEntity.setStatus(resultSet.getString("status_name"));
                taskEntity.setStart(resultSet.getDate("start"));
                taskEntity.setEnd(resultSet.getDate("end"));
                taskEntity.setProject(resultSet.getString("job_name"));
                taskEntities.add(taskEntity);
            }
        }catch(Exception e){
            System.out.print("cant get data getAlljob"+e.getMessage());
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection addJobUser"+e.getMessage());
                }
            }
        }
        return taskEntities;
    }
    public List<Integer> getAllTaskByProjectID(int id){
        Connection connection = null;
        List<Integer> integers= new ArrayList<>();
        try{
            String sql = "select distinct user_id from tasks join jobs on tasks.job_id = jobs.id and jobs.id = ?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                integers.add(resultSet.getInt("user_id"));
            }
        }catch(Exception e){
            System.out.print("cant get data getAlljob"+e.getMessage());
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection addJobUser"+e.getMessage());
                }
            }
        }
        return integers;
    }
}
