package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.BlackListUserService;
import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.repository.BlackListUserServiceRepository;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.config.TestConfigurationServiceTest;
import by.itacademy.boldysh.service.interfaces.BlackListUserServiceService;
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
public class BlackListUserServiceServiceImplTest {

    @Autowired
    DatabaseHelper databaseHelper;

    @Autowired
    BlackListUserServiceRepository blackListUserServiceRepository;

    @Autowired
    UserServiceRepository userServiceRepository;

    @Autowired
    BlackListUserServiceService blackListUserServiceService;

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
                .isAdmin(false)
                .build();
        userServiceRepository.save(userService);
        blackListUserServiceService.save(BlackListUserService.builder()
                .cause("Smoking car")
                .userService(userService)
                .build());
        BlackListUserService blackListUserService = blackListUserServiceRepository.findByUserService(userService);
        System.out.println(blackListUserService);
        assertNotNull(blackListUserService);
    }

    @Test
    public void findAll() {
        List<BlackListUserService> blackListUserServices = blackListUserServiceService.findAll();
        blackListUserServices.forEach(System.out::println);
        assertEquals(1, blackListUserServices.size());
    }

    @Test
    public void delete() {
        UserService userService = userServiceRepository.findByPassportNumber("MP332323232");
        BlackListUserService blackListUserService = blackListUserServiceRepository.findByUserService(userService);
        blackListUserServiceService.delete(blackListUserService);
        BlackListUserService blackListUserService1 = blackListUserServiceRepository.findByUserService(userService);
        assertNull(blackListUserService1);

    }
}