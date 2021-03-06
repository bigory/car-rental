package by.itacademy.boldysh.service.impl;

import by.itacademy.boldysh.database.dto.FilterDto;
import by.itacademy.boldysh.database.entity.BrandCar_;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.Car_;
import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.entity.StatusOrder;
import by.itacademy.boldysh.database.repository.CarRepository;
import by.itacademy.boldysh.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    @Cacheable("allBrandCar")
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return StreamSupport.stream(carRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public void delete(Car car) {
    }

    @Override
    @Cacheable({"cars", "allBrandCar"})
    public Page<Car> findByPaginated(Pageable pageable, List<Car> cars) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Car> listCar;

        if (cars.size() < startItem) {
            listCar = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, cars.size());
            listCar = cars.subList(startItem, toIndex);
        }
        return new PageImpl<>(listCar, PageRequest.of(currentPage, pageSize), cars.size());
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Cacheable({"cars", "allBrandCar"})
    public Page<Car> findByFilterAndPaginationCars(FilterDto filterDto, Pageable page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> carRoot = criteriaQuery.from(Car.class);
        criteriaQuery.select(carRoot).distinct(true);
        Predicate criteria = criteriaBuilder.conjunction();

        final String valueBrandCar = "";

        if (!filterDto.getBrandCar().equals(valueBrandCar)) {
            Predicate brand = criteriaBuilder.equal(carRoot.get(Car_.brandCar).get(BrandCar_.brand), filterDto.getBrandCar());
            criteria = criteriaBuilder.and(criteria, brand);
        }
        if (!filterDto.getModelCar().equals(valueBrandCar)) {
            Predicate modelCar = criteriaBuilder.equal(carRoot.get(Car_.model), filterDto.getModelCar());
            criteria = criteriaBuilder.and(criteria, modelCar);
        }
        if (filterDto.getYearOfIssue() != null) {
            Predicate yearOfIssueCar = criteriaBuilder.equal(carRoot.get(Car_.yearOfIssue), filterDto.getYearOfIssue());
            criteria = criteriaBuilder.and(criteria, yearOfIssueCar);
        }
        if (filterDto.getTransmission() != null) {
            Predicate transmissionCar = criteriaBuilder.equal(carRoot.get(Car_.transmission), filterDto.getTransmission());
            criteria = criteriaBuilder.and(criteria, transmissionCar);
        }
        if (filterDto.getClassCar() != null) {
            Predicate classCar = criteriaBuilder.equal(carRoot.get(Car_.carClass), filterDto.getClassCar());
            criteria = criteriaBuilder.and(criteria, classCar);
        }
        if (filterDto.getCostRentalOfDay() != null) {
            Predicate costRentalOfDayCar = criteriaBuilder.equal(carRoot.get(Car_.costRentalOfDay), filterDto.getCostRentalOfDay());
            criteria = criteriaBuilder.and(criteria, costRentalOfDayCar);
        }
        criteriaQuery.where(criteria);

        TypedQuery<Car> carsList = entityManager.createQuery(criteriaQuery);
        int totalPages = carsList.getResultList().size();

        carsList.setFirstResult(page.getPageNumber() * page.getPageSize());
        carsList.setMaxResults(page.getPageSize());

        Page<Car> carPage = new PageImpl<>(carsList.getResultList(), page, totalPages);

        entityManager.close();
        return carPage;
    }

    @Override
    public List<Car> findByCarNoOrderRental(List<Car> cars, List<OrderRentalCar> orderRentalCarList) {
        List<Car> carList = new ArrayList<>();
        for (Car car : cars) {
            int i = 0;
            for (OrderRentalCar orderRentalCar : orderRentalCarList) {
                if (car.getId().equals(orderRentalCar.getCarId()) & orderRentalCar.getStatusOrder().equals(StatusOrder.COMPLETED)) {
                    carList.add(car);
                    i++;
                    break;
                } else if (car.getId().equals(orderRentalCar.getCarId())) {
                    i++;
                    break;
                }
            }
            if (i == 0) {
                carList.add(car);
            }
        }
        return carList;
    }
}