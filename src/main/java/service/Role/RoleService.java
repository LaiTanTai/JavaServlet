package service.Role;

import Entity.RoleEntity;
import repository.Role.RoleRepository;

import java.util.List;

public class RoleService implements RoleServiceImp {
    private RoleRepository roleRepository = new RoleRepository();
    @Override
    public List<RoleEntity> findAllRole() {
        List<RoleEntity> roleEntityList = roleRepository.findAllRole();
        return roleEntityList;
    }
    public void deleteRole(int role_id){
        roleRepository.deleteRole(role_id);
    }
    public void editRole(int id , String name , String des){
        roleRepository.editRole(id,name,des);
    }
    @Override
    public void addRole(String name ,String des) {
        roleRepository.addRole(name,des);
    }
}
