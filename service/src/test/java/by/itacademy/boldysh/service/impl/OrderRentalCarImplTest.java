package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.service.config.TestConfigurationServiceTest;
import by.itacademy.boldysh.service.interfaces.OrderRentalCarService;
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


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfigurationServiceTest.class)
@Transactional
public class OrderRentalCarImplTest {

    @Autowired
    DatabaseHelper databaseHelper;

    @Autowired
    OrderRentalCarService orderRentalCarService;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findAll() {
        List<OrderRentalCar> orders = orderRentalCarService.findAll();
        assertEquals(2, orders.size());
    }
}