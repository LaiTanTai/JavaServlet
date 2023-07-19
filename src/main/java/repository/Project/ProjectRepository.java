package repository.Project;

import Entity.ProjectEntity;
import config.Mysqlconfig;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements ProjectRepositoryImp{
    @Override
    public void updateProject(int id, String name, Date start, Date end) {
        Connection connection = null;
        try{
            String sql = "update jobs set name=?, start_date = ? ,end_date = ? where id=?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setString(1,name);
            statement.setDate(2,start);
            statement.setDate(3,end);
            statement.setInt(4,id);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.print("cant update data"+e.getMessage());
        }finally {
            if(connection!= null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection"+e.getMessage());
                }
            }
        }
    }

    @Override
    public List<ProjectEntity> getAllProject() {
        Connection connection = null;
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        try{
            String sql = "select * from jobs";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                ProjectEntity project = new ProjectEntity();
                project.setProject_name(resultSet.getString("name"));
                project.setId(resultSet.getInt("id"));
                project.setStart(resultSet.getDate("start_date"));
                project.setEnd(resultSet.getDate("end_date"));
                projectEntityList.add(project);
            }
        }catch (Exception e){
            System.out.print("can find data findAllProject");
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("findAllProject error close connection"+e.getMessage());
                }
            }
        }
        return projectEntityList;
    }

    public void addProject(String name, Date start, Date end) {
        Connection connection = null;
        try{
            String sql = "insert into jobs(name,start_date,end_date) values(?,?,?)";
            PreparedStatement statement= Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setString(1,name);
            statement.setDate(2,start);
            statement.setDate(3,end);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.print("cant add data"+e.getMessage());
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                }catch(Exception e ){
                    System.out.print("cant close connection "+e.getMessage());
                }
            }
        }
    }

    public void deleteProject(int id) {
        Connection connection = null;
        try{
            String sql = "delete from jobs where jobs.id = ?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.print("cant delete data"+e.getMessage());
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                }catch(Exception e ){
                    System.out.print("cant close connection "+e.getMessage());
                }
            }
        }
    }
}
