package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.UserService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceRepository extends PagingAndSortingRepository<UserService, Long> {

    UserService findByPassportNumber(String passportNumber);

   UserService findByEmail(String email);

    UserService findByBlackListUserService(String passportNumber);
}
