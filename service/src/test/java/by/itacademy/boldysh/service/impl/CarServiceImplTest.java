package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import by.itacademy.boldysh.database.repository.BrandCarRepository;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.service.config.TestConfigurationServiceTest;
import by.itacademy.boldysh.service.interfaces.CarService;
import by.itacademy.boldysh.service.interfaces.CustomFilterAndPaginationCars;
import by.itacademy.boldysh.service.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfigurationServiceTest.class)
@Transactional
public class CarServiceImplTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomFilterAndPaginationCars customFilterAndPaginationCars;

    @Autowired
    private BrandCarRepository brandCarRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findAll() {
        List<Car> cars = carService.findAll();
        cars.forEach(System.out::println);
        assertEquals(3, cars.size());
    }

    @Test
    public void save() {
        Car car = Car.builder()
                .brandCar(BrandCar.builder()
                        .brand("BMW")
                        .build())
                .carClass(CarClass.BUSINESS)
                .model("540")
                .transmission(Transmission.MACHINE)
                .costRentalOfDay(50)
                .vinNumber("WADASDQQ123123".toUpperCase())
                .yearOfIssue(2019)
                .build();
        carService.save(car);
        System.out.println(car);
    }

    @Test
    public void updateCar() {
        Integer startCost = carRepository.findByVinNumber("DSDS233232").getCostRentalOfDay();
        carService.updateCar("DSDS233232", 60);
        Integer finishCost = carRepository.findByVinNumber("DSDS233232").getCostRentalOfDay();
        assertTrue(startCost < finishCost);
    }


    @Test
    public void findByFilterAndPaginationCars() {
        Page<Car> cars = customFilterAndPaginationCars.findByFilterAndPaginationCars("", "",
                null, null, null, null, PageRequest.of(0, 2));
        System.out.println(cars.getContent().get(0));
        assertEquals(2, cars.getContent().size());
    }

    @Test
    public void findByPaginated() {
        List<Car> cars = (List<Car>) carRepository.findAll();
        Page<Car> pagesCar = carService.findByPaginated(PageRequest.of(0, 2), cars);
        assertEquals(2, pagesCar.getContent().size());

    }
}