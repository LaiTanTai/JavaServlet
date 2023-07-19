package repository.Role;

import Entity.RoleEntity;

import java.util.List;

public interface RoleRepositoryImp {
    List<RoleEntity> findAllRole();
    void deleteRole(int role_id);
    void editRole(int id , String name , String des);
    void addRole(String name , String des);
}
