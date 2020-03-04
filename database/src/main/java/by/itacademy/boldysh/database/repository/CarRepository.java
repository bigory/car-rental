package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long>, QuerydslPredicateExecutor<Car> {

    List<Car> findByModel(String name);

    Car findByVinNumber(String vinNumber);

    List<Car> findAllByBrandCarBrand(String brandCar);

    List<Car> findAllByBrandCarBrandAndCostRentalOfDayAndCarClassAndTransmission(String brandCar,
                                                                                 Integer costRentalOfDay,
                                                                                 CarClass carClass, Transmission transmission);

    @Transactional
    @Modifying
    void deleteByVinNumber(String vinNumber);

}
