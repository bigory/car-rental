package by.itacademy.boldysh.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AdditionalService.class)
public abstract class AdditionalService_ extends by.itacademy.boldysh.database.entity.BaseEntity_ {

	public static volatile SingularAttribute<AdditionalService, Integer> cost;
	public static volatile ListAttribute<AdditionalService, OrderRentalCar> orderRentalCars;
	public static volatile SingularAttribute<AdditionalService, Services> services;

	public static final String COST = "cost";
	public static final String ORDER_RENTAL_CARS = "orderRentalCars";
	public static final String SERVICES = "services";

}

