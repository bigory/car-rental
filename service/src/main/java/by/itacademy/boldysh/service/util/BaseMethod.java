package by.itacademy.boldysh.service.util;

import by.itacademy.boldysh.database.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseMethod<T extends Serializable, E extends BaseEntity<T>> {

    void save(E object);

    List<E> findAll();

    void delete(E entity);

}
