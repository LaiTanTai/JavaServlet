package repository.User;

import Entity.UserEntity;
import config.Mysqlconfig;
import payload.request.Fullname_Id;
import payload.response.SuccessRole;
import payload.response.UserJobTask;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements UserRepositoryImp {
    @Override
    public Fullname_Id findIdByEmailAndPassword(String email, String password) {
        Connection connection = null;
        Fullname_Id fullnameId = new Fullname_Id();
        try {
            String query_sentense = "select * from users u where u.email = ? and u.password = ?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(query_sentense);
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                fullnameId.setId(resultSet.getInt("id"));
                fullnameId.setName(resultSet.getString("fullname"));
            }
        }catch(Exception e) {
            System.out.print("Error can find Email Password" + e.getMessage());
        }finally{
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.print("Lỗi đóng kết nối findByEmailAndPassword");
                }
            }
        }
        return fullnameId;
    }

    @Override
    public SuccessRole findByEmailAndPassword(String email , String password) {
        Connection connection = null;
        SuccessRole isSuccess = new SuccessRole();
        try {
            String query_sentense = "select * from users where users.email = ? and users.password = ? limit 1";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(query_sentense);
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                isSuccess.setiSsuccess(true);
                isSuccess.setRole_id(resultSet.getInt("role_id"));
            }
        }catch(Exception e) {
            System.out.print("Error can find Email Password" + e.getMessage());
        }finally{
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.print("Lỗi đóng kết nối findByEmailAndPassword");
                }
            }
        }
        return isSuccess;
    }
    @Override
    public List<UserEntity> findAll(){
        Connection connection = null;
        List<UserEntity> testlist = new ArrayList<>();
        try {
            String query_sentense = "select * from users join roles on users.role_id = roles.id";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(query_sentense);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                UserEntity userEntity = new UserEntity();
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setAvatar(resultSet.getString("avatar"));
                userEntity.setRole(resultSet.getInt("role_id"));
                userEntity.setFullname(resultSet.getString("fullname"));
                userEntity.setRoleId(resultSet.getString("name"));
                testlist.add(userEntity);
            }
        }catch(Exception e){
            System.out.print("Không tìm thấy dữ liệu " + e.getMessage());

        }finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.print("Lỗi đóng kết nối findAll");
                    return null;
                }
            }
        }
        return testlist;
    }
    @Override
    public Boolean addUser(String email ,String fullname , String password,int role  ){
        Connection connection = null;
        boolean isSuccess = false;
        try{
            String sql = "insert into users(email,password,fullname,role_id) values(?,?,?,?)";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, fullname);
            statement.setInt(4,role);
            isSuccess = statement.executeUpdate()>0;
        }catch (Exception e){
            System.out.print("Error Field"+e.getMessage());
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.print("close exception"+e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    @Override
    public void deleteUser(int id) {
        Connection connection = null;
        try{
            String sql = "delete from users where users.id = ?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch(Exception e) {
            System.out.print("Cant find the data deleteUser" + e.getMessage());
        }finally {
            if(connection!= null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("Cant close connection deleteUser" + e.getMessage());
                }
            }
        }
    }

    @Override
    public List<UserJobTask> findUserJobTask(int id) {
        Connection connection = null;
        List<UserJobTask> userJobTaskList = new ArrayList<>();
        try {
            String sql = "select tasks.id task_id, tasks.name tasks_name ,tasks.start_date tasks_start, tasks.end_date tasks_end,status.name status_name,jobs.name jobs_name  from tasks join users on tasks.user_id = users.id and users.id = ? join status on tasks.status_id = status.id join jobs on tasks.job_id = jobs.id";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                UserJobTask userJobTask = new UserJobTask();
                userJobTask.setTaskName(resultSet.getString("tasks_name"));
                userJobTask.setJobname(resultSet.getString("jobs_name"));
                userJobTask.setStatus(resultSet.getString("status_name"));
                userJobTask.setStart_date(resultSet.getDate("tasks_start"));
                userJobTask.setEnd_date(resultSet.getDate("tasks_end"));
                userJobTaskList.add(userJobTask);
            }
        }catch (Exception e ) {
            System.out.print("Cant find the data UserJobTask" + e.getMessage());
        }finally{
            if(connection!= null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("Cant close connection UserJobTask" + e.getMessage());
                }
            }
        }
        return userJobTaskList;
    }

    @Override
    public void updateUser(int id, int role_id, String fullname, String email) {
        Connection connection = null;
        try{
            String sql = "update users set role_id=? ,fullname=? ,email=? where id = ?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,role_id);
            statement.setString(2,fullname);
            statement.setString(3,email);
            statement.setInt(4,id);

            if(statement.executeUpdate()>0){
                System.out.print("truy vấn thành công");
            }
        }catch (Exception e){
            System.out.print("cant find data updateUser");
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.print("cant close connection updateUser"+e.getMessage());
                }
            }
        }
    }
}
