package service.User;

import Entity.UserEntity;
import payload.request.UserRespone;
import payload.response.ProfileJobTask;
import payload.response.UserAmountJob;
import payload.response.UserJobTask;
import payload.response.UserTask;

import java.util.List;

public interface UserserviceImp {
    ProfileJobTask findIdByNamePassword(String email, String password);
    List<UserRespone> findAllUser();
    Boolean adduser(String email, String fullname , String password , int role_id);
    void deleteUser(int id);
    UserAmountJob findUser(int id);
    void updateUser(int id ,int role_id, String fullname , String email);
}
