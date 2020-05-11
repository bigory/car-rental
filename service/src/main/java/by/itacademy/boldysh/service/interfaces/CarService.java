package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.dto.FilterDto;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.service.util.BaseMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;


public interface CarService extends BaseMethod<Long, Car> {

    void updateCar(Car car, BigDecimal costRentalOfDay);

    Page<Car> findByFilterAndPaginationCars(FilterDto filterDto, Pageable page);
}
