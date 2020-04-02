package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.BlackListUserService;
import by.itacademy.boldysh.database.repository.BlackListUserServiceRepository;
import by.itacademy.boldysh.service.interfaces.BlackListUserServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class BlackListUserServiceServiceImpl implements BlackListUserServiceService {

    private final BlackListUserServiceRepository blackListUserServiceRepository;

    @Autowired
    public BlackListUserServiceServiceImpl(BlackListUserServiceRepository blackListUserServiceRepository) {
        this.blackListUserServiceRepository = blackListUserServiceRepository;
    }

    @Override
    public void save(BlackListUserService blackListUserService) {
        blackListUserServiceRepository.save(blackListUserService);
    }

    @Override
    public List<BlackListUserService> findAll() {
        return StreamSupport.stream(blackListUserServiceRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(BlackListUserService blackListUserService) {
        blackListUserServiceRepository.delete(blackListUserService);
    }

    @Override
    public Page<BlackListUserService> findByPaginated(Pageable pageable, List<BlackListUserService> entities) {
        return null;
    }
}
