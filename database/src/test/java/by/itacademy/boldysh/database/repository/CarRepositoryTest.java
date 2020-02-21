package by.itacademy.boldysh.database.repository;


import by.itacademy.boldysh.database.config.TestConfiguration;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import by.itacademy.boldysh.database.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class CarRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private CarRepository carRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findByVinNumber() {
        Car car = carRepository.findByVinNumber("ZXS2312121");
        assertNotNull(car);
    }

    @Test
    public void findByModel() {
        List<Car> cars = carRepository.findByModel("750");
        assertEquals(1, cars.size());
    }

    @Test
    public void findByBrandCar() {
        List<Car> list = carRepository.findAllByBrandCarBrand("BMW");
        assertEquals(1, list.size());
    }

    @Test
    public void findAllByBrandCarBrandAndCostRentalOfDayAndCarClassAndTransmission() {
        List<Car> list = carRepository.findAllByBrandCarBrandAndCostRentalOfDayAndCarClassAndTransmission("Volvo",
                45, CarClass.BUSINESS, Transmission.MACHINE);
        assertEquals(1, list.size());
    }

    @Test
    public void delete() {
        carRepository.deleteByVinNumber("ZXS2312121");
        Car car = carRepository.findByVinNumber("ZXS2312121");
        System.out.println(car);
    }
}