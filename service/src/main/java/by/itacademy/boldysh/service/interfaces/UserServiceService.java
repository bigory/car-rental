package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.service.util.BaseMethod;

public interface UserServiceService extends BaseMethod<Long, UserService> {

    void updateUserService(String passportNumber, String paramUserService);

}
