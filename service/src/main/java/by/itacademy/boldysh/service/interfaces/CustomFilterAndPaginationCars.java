package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomFilterAndPaginationCars {

    List<Car> findByFilterCars(String brandCar, String model, Integer yearOfIssue, Transmission transmission, CarClass carClass, Integer costRentalOfDay);

    Page<Car> findByPaginated(Pageable pageable, List<Car> cars);
}