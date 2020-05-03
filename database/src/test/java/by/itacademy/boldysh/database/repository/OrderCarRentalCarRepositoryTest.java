package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.config.TestConfiguration;
import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.entity.StatusOrder;
import by.itacademy.boldysh.database.entity.Transmission;
import by.itacademy.boldysh.database.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class OrderCarRentalCarRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private OrderCarRentalCarRepository orderCarRentalCarRepository;

    public StatusOrder[] getStatusOrder() {
        return StatusOrder.values();
    }

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
    public void updateOrderRentalCar() {
        orderCarRentalCarRepository.update(StatusOrder.EXPECTATION, 1L);
        OrderRentalCar orderRentalCar = orderCarRentalCarRepository.findByCarIdAndUserServiceId(1L, 1L);
        assertEquals(orderRentalCar.getStatusOrder(), StatusOrder.EXPECTATION);
    }

    @Test
    public void findByCarIdAndUserServiceId() {
        OrderRentalCar orderRentalCar = orderCarRentalCarRepository.findByCarIdAndUserServiceId(1L, 1L);
        assertNotNull(orderRentalCar);
    }
    @Test
    public void findById() {
        Optional<OrderRentalCar> orderRentalCar = orderCarRentalCarRepository.findById(1L);
        assertNotNull(orderRentalCar);
    }

}