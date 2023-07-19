package service.Login;

import payload.response.SuccessRole;
import repository.User.UserRepository;

public class Loginservice implements LoginServiceImp {
    private UserRepository userRepository = new UserRepository();
    @Override
    public SuccessRole checkEmailPasswordUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }
}
