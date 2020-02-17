package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.config.TestConfiguration;
import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class UserServiceRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private UserServiceRepository userServiceRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }


    @Test
    public void findByPassportNumber() {
        UserService userService = userServiceRepository.findByPassportNumber("MP332323232");
        System.out.println(userService);
        assertNotNull(userService);
    }


    @Test
    public void findByEmail() {
        UserService userService = userServiceRepository.findByEmail("ivan.ivanov@gmail.com");
        System.out.println(userService);
        assertNotNull(userService);
    }
}
