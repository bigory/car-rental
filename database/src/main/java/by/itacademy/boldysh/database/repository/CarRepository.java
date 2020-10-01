package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.Transmission;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    List<Car> findByModel(String name);

    @Transactional
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Car findByVinNumber(String vinNumber);

    List<Car> findAllByBrandCarBrand(String brandCar);

    List<Car> findAllByBrandCarBrandAndCostRentalOfDayAndCarClassAndTransmission(String brandCar,
                                                                                BigDecimal costRentalOfDay,
                                                                                 CarClass carClass, Transmission transmission);

    List<Car> findByOrderByCostRentalOfDayAsc();

    @Modifying
    @Transactional
    @Query("delete from Car c where c.vinNumber=?1")
    void deleteByVinNumber(String vinNumber);

    @Modifying
    @Transactional
    @Query("update Car c set c.costRentalOfDay=?1 where c.id=?2")
    void update(BigDecimal costRentalOfDay, Long id);
}
