package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.dto.FilterDto;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.service.util.BaseMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CarService extends BaseMethod<Long, Car> {

    Page<Car> findByFilterAndPaginationCars(FilterDto filterDto, Pageable page);
}
