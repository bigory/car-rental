package by.itacademy.boldysh.database.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderRentalCar.class)
public abstract class OrderRentalCar_ extends by.itacademy.boldysh.database.entity.BaseEntity_ {

	public static volatile SingularAttribute<OrderRentalCar, LocalDate> dateStartRental;
	public static volatile SingularAttribute<OrderRentalCar, StatusOrder> statusOrder;
	public static volatile SingularAttribute<OrderRentalCar, Integer> cost;
	public static volatile SingularAttribute<OrderRentalCar, Long> userServiceId;
	public static volatile SingularAttribute<OrderRentalCar, LocalDate> dateFinishRental;
	public static volatile SingularAttribute<OrderRentalCar, AdditionalService> additionalService;
	public static volatile SingularAttribute<OrderRentalCar, Long> carId;

	public static final String DATE_START_RENTAL = "dateStartRental";
	public static final String STATUS_ORDER = "statusOrder";
	public static final String COST = "cost";
	public static final String USER_SERVICE_ID = "userServiceId";
	public static final String DATE_FINISH_RENTAL = "dateFinishRental";
	public static final String ADDITIONAL_SERVICE = "additionalService";
	public static final String CAR_ID = "carId";

}

