package by.itacademy.boldysh.database.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BlackListUserService.class)
public abstract class BlackListUserService_ extends by.itacademy.boldysh.database.entity.BaseEntity_ {

	public static volatile SingularAttribute<BlackListUserService, String> cause;
	public static volatile SingularAttribute<BlackListUserService, Long> id;
	public static volatile SingularAttribute<BlackListUserService, UserService> userService;

	public static final String CAUSE = "cause";
	public static final String ID = "id";
	public static final String USER_SERVICE = "userService";

}

