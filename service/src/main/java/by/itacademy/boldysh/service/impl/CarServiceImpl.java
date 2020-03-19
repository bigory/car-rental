package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.entity.*;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.service.interfaces.CarService;
import by.itacademy.boldysh.service.interfaces.CustomFilterCars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class CarServiceImpl implements CarService, CustomFilterCars {

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

    @PersistenceContext
    private EntityManager entityManager;

    public List<Car> findByFilterCars(String brandCar, String model, Integer yearOfIssue, Transmission transmission, CarClass carClass, Integer costRentalOfDay) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = criteriaQuery.from(Car.class);
        criteriaQuery.select(carRoot).distinct(true);
        int pageSize = 3;
        int pageNumber = 1;

        Predicate criteria = criteriaBuilder.conjunction();

        if (!brandCar.equals("")) {
            Predicate brand = criteriaBuilder.equal(carRoot.get(Car_.brandCar).get(BrandCar_.brand), brandCar);
            criteria = criteriaBuilder.and(criteria, brand);
        }
        if (!model.equals("")) {
            Predicate modelCar = criteriaBuilder.equal(carRoot.get(Car_.model), model);
            criteria = criteriaBuilder.and(criteria, modelCar);
        }
        if (yearOfIssue != null) {
            Predicate yearOfIssueCar = criteriaBuilder.equal(carRoot.get(Car_.yearOfIssue), yearOfIssue);
            criteria = criteriaBuilder.and(criteria, yearOfIssueCar);
        }
        if (transmission != null) {
            Predicate transmissionCar = criteriaBuilder.equal(carRoot.get(Car_.transmission), transmission);
            criteria = criteriaBuilder.and(criteria, transmissionCar);
        }
        if (carClass != null) {
            Predicate classCar = criteriaBuilder.equal(carRoot.get(Car_.carClass), carClass);
            criteria = criteriaBuilder.and(criteria, classCar);
        }
        if (costRentalOfDay != null) {
            Predicate costRentalOfDayCar = criteriaBuilder.equal(carRoot.get(Car_.costRentalOfDay), costRentalOfDay);
            criteria = criteriaBuilder.and(criteria, costRentalOfDayCar);
        }
        criteriaQuery.where(criteria);
        List<Car> cars = entityManager.createQuery(criteriaQuery).setFirstResult(pageNumber - 1).setMaxResults(pageSize).getResultList();

        if (criteria.isNull() == null) {
            cars = StreamSupport.stream(carRepository.findAll().spliterator(), false).collect(Collectors.toList());
        }
        return cars;
    }
}

