package org.narel.dao;

import org.narel.entity.Customer;

import java.util.UUID;

public interface EntityDao<T> {
    T findById(UUID id);

    T create(T entity);

    T update(T entity);

    void delete(UUID id);
}
