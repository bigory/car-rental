package by.itacademy.boldysh.database.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlackListUserService is a Querydsl query type for BlackListUserService
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBlackListUserService extends EntityPathBase<BlackListUserService> {

    private static final long serialVersionUID = -703266290L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlackListUserService blackListUserService = new QBlackListUserService("blackListUserService");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath cause = createString("cause");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUserService userService;

    public QBlackListUserService(String variable) {
        this(BlackListUserService.class, forVariable(variable), INITS);
    }

    public QBlackListUserService(Path<? extends BlackListUserService> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlackListUserService(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlackListUserService(PathMetadata metadata, PathInits inits) {
        this(BlackListUserService.class, metadata, inits);
    }

    public QBlackListUserService(Class<? extends BlackListUserService> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userService = inits.isInitialized("userService") ? new QUserService(forProperty("userService"), inits.get("userService")) : null;
    }

}

