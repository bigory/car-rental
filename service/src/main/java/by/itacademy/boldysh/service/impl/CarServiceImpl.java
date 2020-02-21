package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return StreamSupport.stream(carRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void updateCar(String vinNumber, Integer costRentalOfDay) {
        Car car = carRepository.findByVinNumber(vinNumber);
        car.setCostRentalOfDay(costRentalOfDay);
        carRepository.save(car);
    }

    @Override
    public void delete(Car car) {
    }
}

