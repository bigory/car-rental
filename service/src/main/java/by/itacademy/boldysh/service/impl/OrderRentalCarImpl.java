package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.dto.OrderRentalCarDto;
import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.entity.UserService;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.database.repository.OrderCarRentalCarRepository;
import by.itacademy.boldysh.database.repository.UserServiceRepository;
import by.itacademy.boldysh.service.interfaces.OrderRentalCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class OrderRentalCarImpl implements OrderRentalCarService {

    @Autowired
    OrderCarRentalCarRepository orderCarRentalCarRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserServiceRepository userServiceRepository;

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
    public Page<OrderRentalCar> findByPaginated(Pageable pageable, List<OrderRentalCar> entities) {
        return null;
    }

    @Override
    public Page<OrderRentalCarDto> paginationOrdersRentalCar(Pageable pageable, List<OrderRentalCar> orderRentalCars) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<OrderRentalCarDto> orderRentalCarDtos = new ArrayList<OrderRentalCarDto>();

        for (int i = 0; i < orderRentalCars.size(); i++) {
            Optional<Car> car = carRepository.findById(orderRentalCars.get(i).getCarId());
            Optional<UserService> userService = userServiceRepository.findById(orderRentalCars.get(i).getUserServiceId());

            OrderRentalCarDto orderRentalCarDto = OrderRentalCarDto.builder()
                    .id(orderRentalCars.get(i).getId())
                    .brandCar(car.get().getBrandCar().getBrand())
                    .modelCar(car.get().getModel())
                    .vinNumber(car.get().getVinNumber())
                    .costCar(car.get().getCostRentalOfDay())
                    .firstName(userService.get().getFirstName())
                    .secondName(userService.get().getSecondName())
                    .passportNumber(userService.get().getPassportNumber())
                    .additionalService(orderRentalCars.get(i).getAdditionalService())
                    .costAdditionalService(orderRentalCars.get(i).getAdditionalService().getCost())
                    .startRentalCar(orderRentalCars.get(i).getDateStartRental())
                    .finishRentalCar(orderRentalCars.get(i).getDateFinishRental())
                    .costOrder(car.get().getCostRentalOfDay().add(orderRentalCars.get(i).getAdditionalService().getCost()))
                    .statusOrder(orderRentalCars.get(i).getStatusOrder())
                    .build();
            orderRentalCarDtos.add(orderRentalCarDto);
        }

        List<OrderRentalCarDto> orderRentalCarDtoList;

        if (orderRentalCarDtos.size() < startItem) {
            orderRentalCarDtoList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, orderRentalCarDtos.size());
            orderRentalCarDtoList = orderRentalCarDtos.subList(startItem, toIndex);
        }
        Page<OrderRentalCarDto> orderRentalCarPage = new PageImpl<OrderRentalCarDto>(orderRentalCarDtoList, PageRequest.of(currentPage, pageSize), orderRentalCarDtos.size());

        return orderRentalCarPage;
    }

    @Override
    public OrderRentalCarDto conversionOrderRentalCar(Optional<OrderRentalCar> orderRentalCar, Long id) {
        Optional<Car> car = carRepository.findById(orderRentalCar.get().getCarId());
        Optional<UserService> userService = userServiceRepository.findById(orderRentalCar.get().getUserServiceId());

        OrderRentalCarDto orderRentalCarDto = OrderRentalCarDto.builder()
                .id(orderRentalCar.get().getId())
                .firstName(userService.get().getFirstName())
                .secondName(userService.get().getSecondName())
                .passportNumber(userService.get().getPassportNumber())
                .brandCar(car.get().getBrandCar().getBrand())
                .modelCar(car.get().getModel())
                .vinNumber(car.get().getVinNumber())
                .costCar(car.get().getCostRentalOfDay())
                .additionalService(AdditionalService.builder()
                        .services(orderRentalCar.get().getAdditionalService().getServices())
                        .build())
                .costAdditionalService(orderRentalCar.get().getAdditionalService().getCost())
                .startRentalCar(orderRentalCar.get().getDateStartRental())
                .finishRentalCar(orderRentalCar.get().getDateFinishRental())
                .costOrder(orderRentalCar.get().getCost())
                .statusOrder(orderRentalCar.get().getStatusOrder())
                .build();

        return orderRentalCarDto;
    }
}
