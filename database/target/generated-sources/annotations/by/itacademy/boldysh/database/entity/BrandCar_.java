package by.itacademy.boldysh.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BrandCar.class)
public abstract class BrandCar_ extends by.itacademy.boldysh.database.entity.BaseEntity_ {

	public static volatile ListAttribute<BrandCar, Car> cars;
	public static volatile SingularAttribute<BrandCar, String> brand;

	public static final String CARS = "cars";
	public static final String BRAND = "brand";

}

