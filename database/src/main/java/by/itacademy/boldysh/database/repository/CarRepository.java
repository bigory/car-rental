package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> /*, CustomCarRepository*/ {

    List<Car> findByModel(String name);

    Car findByVinNumber(String vinNumber);

    List<Car> findAllByBrandCar(BrandCar brandCar);

    List<Car> findAllByBrandCarBrandAndCostRentalOfDayAndCarClassAndTransmission(String brandCar,
                                                                                    Integer costRentalOfDay,
                                                                                    CarClass carClass, Transmission transmission);


}
