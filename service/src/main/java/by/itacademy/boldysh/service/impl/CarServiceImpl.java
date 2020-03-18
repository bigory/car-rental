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
import javax.persistence.criteria.*;
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

    public List<Car> findByFilterCars(String model, Integer yearOfIssue, Transmission transmission, CarClass carClass, Integer costRentalOfDay/*, BrandCar brandCar*/) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = criteriaQuery.from(Car.class);
        /*carRoot.fetch(Car_.brandCar, JoinType.LEFT);*/
        criteriaQuery.select(carRoot).distinct(true);

        Predicate criteria = criteriaBuilder.conjunction();

      /*  if (brandCar != null) {
            Predicate brand = criteriaBuilder.equal(carRoot.get(Car_.brandCar), brandCar);
            criteria = criteriaBuilder.and(criteria, brand);
        }*/
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
        List<Car> cars = entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(4).getResultList();

        if (criteria.isNull() == null) {
            cars = StreamSupport.stream(carRepository.findAll().spliterator(), false).collect(Collectors.toList());
        }
        return cars;
    }
}

