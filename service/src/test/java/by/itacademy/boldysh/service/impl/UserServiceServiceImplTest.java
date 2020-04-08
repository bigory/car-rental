package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.config.TestConfigurationServiceTest;
import by.itacademy.boldysh.service.interfaces.UserServiceService;
import by.itacademy.boldysh.service.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfigurationServiceTest.class)
@Transactional
public class UserServiceServiceImplTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private UserServiceService userServiceService;

    @Autowired
    private UserServiceRepository userServiceRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void save() {
        UserService userService = UserService.builder()
                .firstName("Petrov")
                .secondName("Artem")
                .passportNumber("MC132423111")
                .telephone("+37521324431221")
                .email("artem.petrov@gmail.com")
                .password("RTRVR23")
                .role("USER")
                .build();
        userServiceService.save(userService);
        UserService userService1 = userServiceRepository.findByPassportNumber("MC132423111");
        System.out.println(userService1);
        assertNotNull(userService1);
    }

    @Test
    public void findAll() {
        List<UserService> userServices = userServiceService.findAll();
        userServices.forEach(System.out::println);
        assertEquals(3, userServices.size());
    }

    @Test
    public void update() {
        userServiceService.updateUserService("MP332323232", "+375331432543");
        String telephone = userServiceRepository.findByPassportNumber("MP332323232").getTelephone();
        assertEquals("+375331432543", telephone);
    }

    @Test
    public void delete() {
        userServiceService.delete(userServiceRepository.findByPassportNumber("MP332323232"));
        assertNull(userServiceRepository.findByPassportNumber("MP332323232"));
    }
}