package repository.Role;

import Entity.RoleEntity;
import config.Mysqlconfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository implements RoleRepositoryImp {

    @Override
    public List<RoleEntity> findAllRole() {
        Connection connection = null;
        List<RoleEntity> roleEntityList = new ArrayList<>();
        try{
            String sql = "select * from roles";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                RoleEntity role = new RoleEntity();
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                role.setDescription(resultSet.getString("description"));
                roleEntityList.add(role);
            }
        }catch(Exception e){
            System.out.print("Error not find any role value" + e.getMessage());
        }finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.print("Close exception findAllRole"+e.getMessage());
                    return null;
                }
            }
        }
        return roleEntityList;
    }
    public void editRole(int id , String name , String des){
        Connection connection = null;
        try{
            String sql = "update roles set name=?,description=? where id=?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,des);
            statement.setInt(3,id);
            statement.executeUpdate();
        }catch (Exception e){
            System.out.print("cant delete data"+e.getMessage());
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.print("cant close connection");
                }
            }
        }
    }

    @Override
    public void deleteRole(int role_id) {
        Connection connection = null;
        try{
            String sql = "delete from roles where roles.id = ?";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setInt(1,role_id);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.print("cant delete user"+e.getMessage());
        }finally {
            if(connection!=null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print("cant close connection");
                }
            }
        }
    }

    public void addRole(String name , String des){
        Connection connection = null;
        try{
            String sql = "insert into roles(name,description) values(?,?)";
            PreparedStatement statement = Mysqlconfig.getConnection().prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,des);
            statement.executeUpdate();
        }catch(Exception e){
            System.out.print("cant add data addrole"+e.getMessage());
        }finally{
            if(connection!=null){
                try{
                    connection.close();
                }catch(Exception e){
                    System.out.print(e.getMessage());
                }
            }
        }
    }
}
