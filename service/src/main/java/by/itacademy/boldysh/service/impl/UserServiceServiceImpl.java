package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.interfaces.UserServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceServiceImpl implements UserServiceService {


    private final UserServiceRepository userServiceRepository;

    @Override
    public void save(UserService userService) {
        userServiceRepository.save(userService);
    }

    @Override
    public List<UserService> findAll() {
        return StreamSupport.stream(userServiceRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void updateUserService(String vinNumber, String paramUserService) {
        UserService userService = userServiceRepository.findByPassportNumber(vinNumber);
        userService.setTelephone(paramUserService);
        userServiceRepository.save(userService);
    }

    @Override
    public void delete(UserService userService) {
        userServiceRepository.delete(userService);
    }

    @Override
    public Page<UserService> findByPaginated(Pageable pageable, List<UserService> userService) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserService> listUserService;

        if (userService.size() < startItem) {
            listUserService = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, userService.size());
            listUserService = userService.subList(startItem, toIndex);
        }

        Page<UserService> pageUserService = new PageImpl<UserService>(listUserService, PageRequest.of(currentPage, pageSize), userService.size());

        return pageUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.of(email)
                .map(userServiceRepository::findByEmail)
                .map(user -> User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .authorities(user.getRole())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

