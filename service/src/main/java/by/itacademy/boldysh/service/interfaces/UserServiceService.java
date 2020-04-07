package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.service.util.BaseMethod;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceService extends BaseMethod<Long, UserService>, UserDetailsService {

    void updateUserService(String passportNumber, String paramUserService);

}
