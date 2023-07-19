package service.Login;

import payload.response.SuccessRole;

public interface LoginServiceImp {
    SuccessRole checkEmailPasswordUser(String email, String password);
}
