package by.itacademy.boldysh.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Car.class)
public abstract class Car_ extends by.itacademy.boldysh.database.entity.BaseEntity_ {

	public static volatile SingularAttribute<Car, BrandCar> brandCar;
	public static volatile SingularAttribute<Car, Transmission> transmission;
	public static volatile SingularAttribute<Car, String> vinNumber;
	public static volatile ListAttribute<Car, UserService> userServices;
	public static volatile SingularAttribute<Car, String> model;
	public static volatile SingularAttribute<Car, Integer> yearOfIssue;
	public static volatile SingularAttribute<Car, Integer> costRentalOfDay;
	public static volatile SingularAttribute<Car, CarClass> carClass;

	public static final String BRAND_CAR = "brandCar";
	public static final String TRANSMISSION = "transmission";
	public static final String VIN_NUMBER = "vinNumber";
	public static final String USER_SERVICES = "userServices";
	public static final String MODEL = "model";
	public static final String YEAR_OF_ISSUE = "yearOfIssue";
	public static final String COST_RENTAL_OF_DAY = "costRentalOfDay";
	public static final String CAR_CLASS = "carClass";

}

