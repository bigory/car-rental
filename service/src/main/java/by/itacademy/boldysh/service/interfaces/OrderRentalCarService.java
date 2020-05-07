package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.dto.OrderRentalCarDto;
import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.service.util.BaseMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface OrderRentalCarService extends BaseMethod<Long, OrderRentalCar> {

    Page<OrderRentalCarDto> paginationOrdersRentalCar(Pageable page, List<OrderRentalCar> orderRentalCars);
}
