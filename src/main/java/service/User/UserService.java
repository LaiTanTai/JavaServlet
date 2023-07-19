package service.User;

import Entity.UserEntity;
import payload.request.Fullname_Id;
import payload.request.UserRespone;
import payload.response.ProfileJobTask;
import payload.response.UserAmountJob;
import payload.response.UserJobTask;
import payload.response.UserTask;
import repository.Task.TaskRepository;
import repository.User.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserserviceImp {
    private UserRepository userRepository = new UserRepository();
    private TaskRepository taskRepository = new TaskRepository();

    @Override
    public ProfileJobTask findIdByNamePassword(String email, String password) {
        Fullname_Id fullnameId = userRepository.findIdByEmailAndPassword(email,password);
        List<UserTask> userTasks = taskRepository.findTaskByUserId(fullnameId.getId());
        ProfileJobTask profileJobTask = new ProfileJobTask();
        profileJobTask.setUserTaskList(userTasks);
        profileJobTask.setFullname(fullnameId.getName());
        double Done= 0 ;
        double NotDone = 0 ;
        double Inprocess  = 0 ;
        for(UserTask item : userTasks){
            if(item.getStatus().equals("Chưa thực hiện")){
                NotDone++;
            }else if(item.getStatus().equals("Đang thực hiện")){
                Inprocess++;
            }else{
                Done++;
            }
        }
        profileJobTask.setNotDone((int) (NotDone/((float)userTasks.size())*100));
        profileJobTask.setInprocess((int) (Inprocess/((float)userTasks.size())*100));
        profileJobTask.setDone((int) (Done/((float)userTasks.size())*100));
       return profileJobTask;
    }
    @Override
    public List<UserRespone> findAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserRespone> userResponesList = new ArrayList<>();
        for(UserEntity item : userEntityList ){
            UserRespone userRespone = new UserRespone();
            userRespone.setId(item.getId());
            userRespone.setRole_id(item.getRole());
            userRespone.setAvatar(item.getAvatar());
            userRespone.setRole(item.getRoleId());
            userRespone.setEmail(item.getEmail());
            userRespone.setFullname(item.getFullname());
            userResponesList.add(userRespone);
        }
        return userResponesList;
    }

    @Override
    public Boolean adduser(String email, String fullname , String password , int role_id) {
        Boolean isSuccess = false;
            isSuccess = userRepository.addUser(email,fullname,password,role_id);

        return isSuccess;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    @Override
    public UserAmountJob findUser(int id) {
        List<UserJobTask> userJobTaskList= userRepository.findUserJobTask(id);
        UserAmountJob userAmountJob = new UserAmountJob();
        userAmountJob.setUserJobTaskList(userJobTaskList);
        double Done= 0 ;
        double NotDone = 0 ;
        double Inprocess  = 0 ;
        for(UserJobTask item : userJobTaskList){
            if(item.getStatus().equals("Chưa thực hiện")){
                NotDone++;
            }else if(item.getStatus().equals("Đang thực hiện")){
                Inprocess++;
            }else{
                Done++;
            }
        }
        userAmountJob.setNotDone((int) (NotDone/((float)userJobTaskList.size())*100));
        userAmountJob.setInprocess((int) (Inprocess/((float)userJobTaskList.size())*100));
        userAmountJob.setDone((int) (Done/((float)userJobTaskList.size())*100));
        return userAmountJob;
    }

    @Override
    public void updateUser(int id, int role_id, String fullname, String email) {
        userRepository.updateUser(id,role_id,fullname,email);
        System.out.print("da hoat ddongj");
    }

}
