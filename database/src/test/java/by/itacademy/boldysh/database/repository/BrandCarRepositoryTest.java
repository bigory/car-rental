package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.config.TestConfiguration;
import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class BrandCarRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private BrandCarRepository brandCarRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findByBrandCar() {
        BrandCar brandCars = brandCarRepository.findByBrand("BMW");
        assertEquals("BMW", brandCars.getBrand());
    }

    @Test
    public void deleteBrandCarByBrand() {
        BrandCar brandCars = brandCarRepository.findByBrand("BMW");
        brandCarRepository.deleteBrandCarByBrand(brandCars.getBrand());
        BrandCar brandCar = brandCarRepository.findByBrand("BMW");
        assertNull(brandCar);

    }
}