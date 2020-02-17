package by.itacademy.boldysh.database.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBrandCar is a Querydsl query type for BrandCar
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBrandCar extends EntityPathBase<BrandCar> {

    private static final long serialVersionUID = 1202203502L;

    public static final QBrandCar brandCar = new QBrandCar("brandCar");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath brand = createString("brand");

    public final ListPath<Car, QCar> cars = this.<Car, QCar>createList("cars", Car.class, QCar.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBrandCar(String variable) {
        super(BrandCar.class, forVariable(variable));
    }

    public QBrandCar(Path<? extends BrandCar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBrandCar(PathMetadata metadata) {
        super(BrandCar.class, metadata);
    }

}

