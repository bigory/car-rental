package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.BlackListUserService;
import by.itacademy.boldysh.database.entity.UserService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListUserServiceRepository extends PagingAndSortingRepository<BlackListUserService, Long> {

    BlackListUserService findByUserService(UserService userService);

    BlackListUserService findByUserServiceId(Long id);

}
