package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.entity.StatusOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface OrderCarRentalCarRepository extends PagingAndSortingRepository<OrderRentalCar, Long> {

    OrderRentalCar findByDateStartRentalAndDateFinishRental(LocalDate startRentalCar, LocalDate finishRentalCar);

    OrderRentalCar findByCarIdAndUserServiceId(Long carId, Long userServiceId);

    OrderRentalCar deleteOrderRentalCarByCarIdAndUserServiceIdAndDateStartRentalAndDateFinishRental(Long carId, Long userServiceId, LocalDate dateStartRentalCar, LocalDate dateFinishRentalCar);

    @Modifying
    @Transactional
    @Query("update OrderRentalCar o set o.statusOrder=?1 where o.id=?2")
    void update(StatusOrder statusOrder, Long id);

}
