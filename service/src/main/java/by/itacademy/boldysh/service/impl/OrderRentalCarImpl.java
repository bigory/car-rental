package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.repository.OrderCarRentalCarRepository;
import by.itacademy.boldysh.service.interfaces.OrderRentalCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class OrderRentalCarImpl implements OrderRentalCarService {

    @Autowired
    OrderCarRentalCarRepository orderCarRentalCarRepository;

    @Override
    public void save(OrderRentalCar orderRentalCar) {
        orderCarRentalCarRepository.save(orderRentalCar);
    }

    @Override
    public List<OrderRentalCar> findAll() {
        return StreamSupport.stream(orderCarRentalCarRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(OrderRentalCar entity) {
    }

    @Override
    public Page<OrderRentalCar> findByPaginated(Pageable pageable, List<OrderRentalCar> orderRentalCars) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<OrderRentalCar> orderRentalCarsList;

        if (orderRentalCars.size() < startItem) {
            orderRentalCarsList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, orderRentalCars.size());
            orderRentalCarsList = orderRentalCars.subList(startItem, toIndex);
        }
        Page<OrderRentalCar> orderRentalCarPage = new PageImpl<OrderRentalCar>(orderRentalCarsList, PageRequest.of(currentPage, pageSize), orderRentalCars.size());
        return orderRentalCarPage;
    }
}
