package by.itacademy.boldysh.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserService.class)
public abstract class UserService_ extends by.itacademy.boldysh.database.entity.BaseEntity_ {

	public static volatile SingularAttribute<UserService, String> passportNumber;
	public static volatile SingularAttribute<UserService, String> firstName;
	public static volatile ListAttribute<UserService, Car> cars;
	public static volatile SingularAttribute<UserService, String> password;
	public static volatile SingularAttribute<UserService, BlackListUserService> blackListUserService;
	public static volatile SingularAttribute<UserService, String> telephone;
	public static volatile SingularAttribute<UserService, Boolean> isAdmin;
	public static volatile SingularAttribute<UserService, String> email;
	public static volatile SingularAttribute<UserService, String> secondName;

	public static final String PASSPORT_NUMBER = "passportNumber";
	public static final String FIRST_NAME = "firstName";
	public static final String CARS = "cars";
	public static final String PASSWORD = "password";
	public static final String BLACK_LIST_USER_SERVICE = "blackListUserService";
	public static final String TELEPHONE = "telephone";
	public static final String IS_ADMIN = "isAdmin";
	public static final String EMAIL = "email";
	public static final String SECOND_NAME = "secondName";

}

