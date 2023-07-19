package repository.User;

import Entity.UserEntity;
import payload.request.Fullname_Id;
import payload.request.UserRespone;
import payload.response.SuccessRole;
import payload.response.UserJobTask;

import java.util.List;

public interface UserRepositoryImp {
    Fullname_Id findIdByEmailAndPassword(String email, String password);
    SuccessRole findByEmailAndPassword(String email, String password);
    List<UserEntity> findAll();
    Boolean addUser(String email ,String fullname , String password,int role  );
    void deleteUser(int id);
    List<UserJobTask> findUserJobTask(int id);
    void updateUser(int id,int role_id , String fullname , String email);
}
