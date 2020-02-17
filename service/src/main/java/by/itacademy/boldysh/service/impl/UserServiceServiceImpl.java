package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.interfaces.UserServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class UserServiceServiceImpl implements UserServiceService {

    private final UserServiceRepository userServiceRepository;

    @Autowired
    public UserServiceServiceImpl(UserServiceRepository userServiceRepository) {
        this.userServiceRepository = userServiceRepository;
    }


    @Override
    public void save(UserService userService) {
        userServiceRepository.save(userService);
    }

    @Override
    public List<UserService> findAll() {
        return StreamSupport.stream(userServiceRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void updateUserService(String vinNumber, String paramUserService) {
        UserService userService = userServiceRepository.findByPassportNumber(vinNumber);
        userService.setTelephone(paramUserService);
        userServiceRepository.save(userService);


    }

    @Override
    public void delete(UserService userService) {
        userServiceRepository.delete(userService);
    }

}
