package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.config.TestConfiguration;
import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.Services;
import by.itacademy.boldysh.database.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class AdditionalServiceRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findByServices() {
        AdditionalService additionalServices = additionalServiceRepository.findByServices(Services.NAVIGATOR);
        System.out.println(additionalServices);
        assertNotNull(additionalServices);
    }

    @Test
    public void findByCost() {
        List<AdditionalService> services = additionalServiceRepository.findByCost(10);
        assertNotNull(services);
    }
}