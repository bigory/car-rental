package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.OrderRentalCar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OrderCarRentalCarRepository extends PagingAndSortingRepository<OrderRentalCar, Long> {

    OrderRentalCar findByDateStartRentalAndDateFinishRental(LocalDate startRentalCar, LocalDate finishRentalCar);


}
