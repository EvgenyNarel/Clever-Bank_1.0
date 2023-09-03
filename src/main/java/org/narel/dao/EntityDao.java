package org.narel.dao;


import org.narel.entity.Entity;

import java.util.UUID;
/**
 *
 * @param <T>
 */
public interface EntityDao<T extends Entity> {
    /**
     *
     * @param id
     * @return
     */
    T findById(UUID id);
    /**
     *
     * @param entity
     * @return
     */
    T create(T entity);
    /**
     *
     * @param entity
     * @return
     */
    T update(T entity);
    /**
     *
     * @param id
     * @return
     */
    void delete(UUID id);
}
