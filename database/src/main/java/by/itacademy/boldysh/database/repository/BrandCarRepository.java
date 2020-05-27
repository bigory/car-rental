package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.BrandCar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BrandCarRepository extends PagingAndSortingRepository<BrandCar, Long> {

    BrandCar findByBrand(String brandCar);

    @Transactional
    void deleteBrandCarByBrand(String brandCar);

}
