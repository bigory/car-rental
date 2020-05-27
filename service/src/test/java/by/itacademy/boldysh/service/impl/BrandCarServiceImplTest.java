package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.repository.BrandCarRepository;
import by.itacademy.boldysh.service.config.TestConfigurationServiceTest;
import by.itacademy.boldysh.service.interfaces.BrandCarService;
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
public class BrandCarServiceImplTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private BrandCarService brandCarService;

    @Autowired
    private BrandCarRepository brandCarRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void save() {
        BrandCar brandCar = BrandCar.builder()
                .brand("Toyota")
                .build();
        brandCarService.save(brandCar);
        System.out.println(brandCarRepository.findByBrand("Toyota"));
        assertNotNull(brandCarRepository.findByBrand("Toyota"));
    }

    @Test
    public void findAll() {
        List<BrandCar> brandCars = brandCarService.findAll();
        brandCars.forEach(System.out::println);
        List<BrandCar> brandCars1 = brandCarService.findAll();
        brandCars1.forEach(System.out::println);
        assertEquals(brandCars1.size(), brandCars.size());
    }

    @Test
    public void delete() {
        BrandCar brandCar = brandCarRepository.findByBrand("BMW");
        brandCarService.delete(brandCar);
        BrandCar brandCar1 = brandCarRepository.findByBrand("BMW");
        assertNull(brandCar1);
    }
}