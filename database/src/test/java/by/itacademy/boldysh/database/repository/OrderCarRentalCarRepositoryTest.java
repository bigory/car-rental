package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.config.TestConfiguration;
import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class OrderCarRentalCarRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private OrderCarRentalCarRepository orderCarRentalCarRepository;


    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findByDateStartRentalAndDateFinishRental() {
        OrderRentalCar orderRentalCar = orderCarRentalCarRepository.findByDateStartRentalAndDateFinishRental(LocalDate.of(2019, 10, 10), LocalDate.of(2019, 10, 20));
        System.out.println(orderRentalCar);
        assertNotNull(orderRentalCar);

    }

    @Test
    public void findByCarIdAndUserServiceId() {
        OrderRentalCar orderRentalCar = orderCarRentalCarRepository.findByCarIdAndUserServiceId(1L, 1L);
        assertNull(orderRentalCar);
    }

    @Test
    public void findById() {
        Optional<OrderRentalCar> orderRentalCar = orderCarRentalCarRepository.findById(1L);
        assertNotNull(orderRentalCar);
    }

    @Test
    public void findAllByUserServiceEmail() {
        List<OrderRentalCar> orderRentalCarList = orderCarRentalCarRepository.findAllByUserServiceEmail(1L);
        assertNotNull(orderRentalCarList);
    }
}