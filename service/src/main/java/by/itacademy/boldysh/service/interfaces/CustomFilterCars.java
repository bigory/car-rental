package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;

import java.util.List;

public interface CustomFilterCars {

    List<Car> findByFilterCars(String brandCar, String model, Integer yearOfIssue, Transmission transmission, CarClass carClass, Integer costRentalOfDay);
}
