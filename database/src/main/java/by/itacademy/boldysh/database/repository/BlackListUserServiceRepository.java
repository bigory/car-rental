package by.itacademy.boldysh.database.repository;

import by.itacademy.boldysh.database.entity.BlackListUserService;
import by.itacademy.boldysh.database.entity.UserService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BlackListUserServiceRepository extends PagingAndSortingRepository<BlackListUserService, Long> {

    BlackListUserService findByUserService(UserService userService);

    BlackListUserService findByUserServiceId(Long id);


    @Modifying
    @Transactional
    @Query("delete from BlackListUserService b where b.userService=?1")
    void deleteByBlackListUserService(UserService userService);
}
