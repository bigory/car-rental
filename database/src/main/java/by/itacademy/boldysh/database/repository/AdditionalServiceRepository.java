package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.Services;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalServiceRepository extends PagingAndSortingRepository<AdditionalService, Long> {

    AdditionalService findByServices(Services services);

    List<AdditionalService> findByCost(Integer cost);

}
