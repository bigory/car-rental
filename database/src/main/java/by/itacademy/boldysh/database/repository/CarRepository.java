package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    List<Car> findByModel(String name);

    Car findByVinNumber(String vinNumber);

    List<Car> findAllByBrandCarBrand(String brandCar);

    List<Car> findAllByBrandCarBrandAndCostRentalOfDayAndCarClassAndTransmission(String brandCar,
                                                                                 Integer costRentalOfDay,
                                                                                 CarClass carClass, Transmission transmission);

    List<Car> findByOrderByCostRentalOfDayAsc();

    @Modifying
    @Transactional
    @Query("delete from Car c where c.vinNumber=?1")
    void deleteByVinNumber(String vinNumber);
}
