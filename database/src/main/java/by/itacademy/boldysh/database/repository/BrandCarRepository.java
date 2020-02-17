package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.BrandCar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandCarRepository extends PagingAndSortingRepository<BrandCar, Long> {

    BrandCar findByBrand(String brandCar);

}
