package org.narel.saver;

import org.narel.model.Period;
import org.narel.model.dto.ExtractDto;
import org.narel.model.dto.MoneyStatementDto;
import org.narel.model.dto.OperationDto;

/**
 *
 */
public interface CheckSaver {

    /**
     *
     * @param operation
     */
    void save(OperationDto operation);

    /**
     *
     * @param extract
     */
    void save(ExtractDto extract);

    /**
     *
     * @param moneyStatement
     */
    void save(MoneyStatementDto moneyStatement, Period period);
}
