package org.narel.dao;

import org.narel.model.OperationDto;

import java.util.List;
import java.util.UUID;

public interface OperationDao extends EntityDao<OperationDto> {

    List<OperationDto> getByCustomerId(UUID customerId);
}

