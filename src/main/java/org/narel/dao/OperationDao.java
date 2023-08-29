package org.narel.dao;

import org.narel.entity.Operation;

import java.util.List;
import java.util.UUID;

public interface OperationDao extends EntityDao<Operation> {

    List<Operation> getByCustomerId(UUID customerId);

}
