package by.itacademy.boldysh.database.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdditionalService is a Querydsl query type for AdditionalService
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdditionalService extends EntityPathBase<AdditionalService> {

    private static final long serialVersionUID = 1523872013L;

    public static final QAdditionalService additionalService = new QAdditionalService("additionalService");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<OrderRentalCar, QOrderRentalCar> orderRentalCars = this.<OrderRentalCar, QOrderRentalCar>createList("orderRentalCars", OrderRentalCar.class, QOrderRentalCar.class, PathInits.DIRECT2);

    public final EnumPath<Services> services = createEnum("services", Services.class);

    public QAdditionalService(String variable) {
        super(AdditionalService.class, forVariable(variable));
    }

    public QAdditionalService(Path<? extends AdditionalService> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdditionalService(PathMetadata metadata) {
        super(AdditionalService.class, metadata);
    }

}

