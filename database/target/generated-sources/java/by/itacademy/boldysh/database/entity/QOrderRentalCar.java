package by.itacademy.boldysh.database.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderRentalCar is a Querydsl query type for OrderRentalCar
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderRentalCar extends EntityPathBase<OrderRentalCar> {

    private static final long serialVersionUID = 1195699267L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderRentalCar orderRentalCar = new QOrderRentalCar("orderRentalCar");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QAdditionalService additionalService;

    public final NumberPath<Long> carId = createNumber("carId", Long.class);

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    public final DatePath<java.time.LocalDate> dateFinishRental = createDate("dateFinishRental", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> dateStartRental = createDate("dateStartRental", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<StatusOrder> statusOrder = createEnum("statusOrder", StatusOrder.class);

    public final NumberPath<Long> userServiceId = createNumber("userServiceId", Long.class);

    public QOrderRentalCar(String variable) {
        this(OrderRentalCar.class, forVariable(variable), INITS);
    }

    public QOrderRentalCar(Path<? extends OrderRentalCar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderRentalCar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderRentalCar(PathMetadata metadata, PathInits inits) {
        this(OrderRentalCar.class, metadata, inits);
    }

    public QOrderRentalCar(Class<? extends OrderRentalCar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.additionalService = inits.isInitialized("additionalService") ? new QAdditionalService(forProperty("additionalService")) : null;
    }

}

