package org.narel.dao;

import org.narel.entity.Operation;

import java.util.List;
import java.util.UUID;

public interface OperationDao extends EntityDao<Operation> {

    /**
     *
     * @param customerId
     * @return
     */
    List<Operation> getByAccountId(UUID customerId);
}

