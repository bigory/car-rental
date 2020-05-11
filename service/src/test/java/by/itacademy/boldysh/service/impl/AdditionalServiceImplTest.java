package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.Services;
import by.itacademy.boldysh.database.repository.AdditionalServiceRepository;
import by.itacademy.boldysh.service.config.TestConfigurationServiceTest;
import by.itacademy.boldysh.service.interfaces.AdditionalServiceService;
import by.itacademy.boldysh.service.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfigurationServiceTest.class)
@Transactional
public class AdditionalServiceImplTest {

    @Autowired
    DatabaseHelper databaseHelper;

    @Autowired
    AdditionalServiceRepository additionalServiceRepository;

    @Autowired
    AdditionalServiceService additionalServiceService;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void save() {
        additionalServiceService.save(AdditionalService.builder()
                .services(Services.CHARGER)
                .cost(BigDecimal.valueOf(20))
                .build());
        AdditionalService additionalServiceSaves = additionalServiceRepository.findByServices(Services.CHARGER);
        System.out.println(additionalServiceSaves);
        assertNotNull(additionalServiceSaves);
    }

    @Test
    public void findAll() {
        List<AdditionalService> additionalServices = additionalServiceService.findAll();
        additionalServices.forEach(System.out::println);
        assertEquals(2, additionalServices.size());
    }

    @Test
    public void delete() {
        additionalServiceService.delete(additionalServiceRepository.findByServices(Services.NAVIGATOR));
        AdditionalService additionalService = additionalServiceRepository.findByServices(Services.NAVIGATOR);
        assertNull(additionalService);
    }

    @Test
    public void updateAdditionalService() {
        BigDecimal oldCost = additionalServiceRepository.findByServices(Services.NAVIGATOR).getCost();
        additionalServiceService.updateAdditionalService(Services.NAVIGATOR, BigDecimal.valueOf(50));
        BigDecimal newCost = additionalServiceRepository.findByServices(Services.NAVIGATOR).getCost();
        assertTrue(oldCost.equals(newCost));

    }
}