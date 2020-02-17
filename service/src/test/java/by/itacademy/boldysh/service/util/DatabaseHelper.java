package by.itacademy.boldysh.service.util;

import by.itacademy.boldysh.database.entity.AdditionalService;
import by.itacademy.boldysh.database.entity.BlackListUserService;
import by.itacademy.boldysh.database.entity.BrandCar;
import by.itacademy.boldysh.database.entity.Car;
import by.itacademy.boldysh.database.entity.CarClass;
import by.itacademy.boldysh.database.entity.OrderRentalCar;
import by.itacademy.boldysh.database.entity.Services;
import by.itacademy.boldysh.database.entity.StatusOrder;
import by.itacademy.boldysh.database.entity.Transmission;
import by.itacademy.boldysh.database.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

@Component
public class DatabaseHelper {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public DatabaseHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void cleanDatabase() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from BlackListUserService").executeUpdate();
        entityManager.createQuery("delete from UserService").executeUpdate();
        entityManager.createQuery("delete from Car").executeUpdate();
        entityManager.createQuery("delete from BrandCar ").executeUpdate();
        entityManager.createQuery("delete from OrderRentalCar").executeUpdate();
        entityManager.createQuery("delete  from AdditionalService").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void prepareData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        UserService userOne = new UserService("Ivanov", "Ivan", "MC123123213", "+375255467654", "ivan.ivanov@gmail.com", "QWE12345678", false);
        UserService userTwo = new UserService("Petrov", "Petr", "MC232323232", "+375295467654", "petr.petrov@gmail.com", "QQE12345678", false);
        UserService userThree = new UserService("Sidorova", "Vera", "MP332323232", "+375335463241", "vera.sidorova@gmail.com", "ASE12345678", false);
        entityManager.persist(userOne);
        entityManager.persist(userTwo);
        entityManager.persist(userThree);

        BlackListUserService blackListUserServiceThree = new BlackListUserService("Max speed", userThree);
        entityManager.persist(blackListUserServiceThree);

        BrandCar brandCarBMW = new BrandCar("BMW");
        BrandCar brandCarVW = new BrandCar("VW");
        BrandCar brandCarVolvo = new BrandCar("Volvo");
        entityManager.persist(brandCarBMW);
        entityManager.persist(brandCarVW);
        entityManager.persist(brandCarVolvo);

        Car bmw = new Car("750", 2019, "DSDS233232", Transmission.MACHINE, CarClass.STANDART, 30, brandCarBMW);
        Car vw = new Car("Polo", 2019, "VFS211312213", Transmission.MECHANIC, CarClass.COMFORT, 20, brandCarVW);
        Car volvo = new Car("S80", 2017, "ZXS2312121", Transmission.MACHINE, CarClass.BUSINESS, 45, brandCarVolvo);
        entityManager.persist(bmw);
        entityManager.persist(vw);
        entityManager.persist(volvo);

        AdditionalService additionalServiceOne = new AdditionalService(Services.BABYCHAIR, 10);
        AdditionalService additionalServiceTwo = new AdditionalService(Services.NAVIGATOR, 20);
        entityManager.persist(additionalServiceOne);
        entityManager.persist(additionalServiceTwo);

        OrderRentalCar orderRentalCarOne = new OrderRentalCar(userOne.getId(), bmw.getId(), LocalDate.of(2019, 10, 10),
                LocalDate.of(2019, 10, 20), 75, StatusOrder.ACCEPTED, additionalServiceOne);
        OrderRentalCar orderRentalCarTwo = new OrderRentalCar(userTwo.getId(), volvo.getId(), LocalDate.of(2019, 10, 20),
                LocalDate.of(2019, 10, 23), 75, StatusOrder.ACCEPTED, additionalServiceOne);
        entityManager.persist(orderRentalCarOne);
        entityManager.persist(orderRentalCarTwo);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
