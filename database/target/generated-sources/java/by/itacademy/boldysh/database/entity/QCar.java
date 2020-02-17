package by.itacademy.boldysh.database.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCar is a Querydsl query type for Car
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCar extends EntityPathBase<Car> {

    private static final long serialVersionUID = -6820205L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCar car = new QCar("car");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBrandCar brandCar;

    public final EnumPath<CarClass> carClass = createEnum("carClass", CarClass.class);

    public final NumberPath<Integer> costRentalOfDay = createNumber("costRentalOfDay", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath model = createString("model");

    public final EnumPath<Transmission> transmission = createEnum("transmission", Transmission.class);

    public final ListPath<UserService, QUserService> userServices = this.<UserService, QUserService>createList("userServices", UserService.class, QUserService.class, PathInits.DIRECT2);

    public final StringPath vinNumber = createString("vinNumber");

    public final NumberPath<Integer> yearOfIssue = createNumber("yearOfIssue", Integer.class);

    public QCar(String variable) {
        this(Car.class, forVariable(variable), INITS);
    }

    public QCar(Path<? extends Car> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCar(PathMetadata metadata, PathInits inits) {
        this(Car.class, metadata, inits);
    }

    public QCar(Class<? extends Car> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brandCar = inits.isInitialized("brandCar") ? new QBrandCar(forProperty("brandCar")) : null;
    }

}

