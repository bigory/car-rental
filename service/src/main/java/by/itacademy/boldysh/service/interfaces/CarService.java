package by.itacademy.boldysh.service.interfaces;

import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.service.util.BaseMethod;

public interface CarService extends BaseMethod<Long, Car> {

    void updateCar(String vinNumber, Integer costRentalOfDay);
}
