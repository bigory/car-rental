package by.itacademy.boldysh.database.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserService is a Querydsl query type for UserService
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserService extends EntityPathBase<UserService> {

    private static final long serialVersionUID = 1956440105L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserService userService = new QUserService("userService");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBlackListUserService blackListUserService;

    public final ListPath<Car, QCar> cars = this.<Car, QCar>createList("cars", Car.class, QCar.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAdmin = createBoolean("isAdmin");

    public final StringPath passportNumber = createString("passportNumber");

    public final StringPath password = createString("password");

    public final StringPath secondName = createString("secondName");

    public final StringPath telephone = createString("telephone");

    public QUserService(String variable) {
        this(UserService.class, forVariable(variable), INITS);
    }

    public QUserService(Path<? extends UserService> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserService(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserService(PathMetadata metadata, PathInits inits) {
        this(UserService.class, metadata, inits);
    }

    public QUserService(Class<? extends UserService> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blackListUserService = inits.isInitialized("blackListUserService") ? new QBlackListUserService(forProperty("blackListUserService"), inits.get("blackListUserService")) : null;
    }

}

