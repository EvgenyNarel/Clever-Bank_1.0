package org.narel.service;

import org.narel.model.Period;
import org.narel.model.dto.ExtractDto;
import org.narel.model.dto.MoneyStatementDto;
import org.narel.model.dto.OperationDto;

import java.math.BigDecimal;

/**
 *
 */
public interface AccountService {

    /**
     *
     * @param accountNumber
     * @param amount
     * @return
     */
    OperationDto topUp(String accountNumber, BigDecimal amount);

    /**
     *
     * @param accountNumber
     * @param amount
     * @return
     */
    OperationDto withdraw(String accountNumber, BigDecimal amount);

    /**
     *
     * @param recipientAccountNumber
     * @param senderAccountNumber
     * @param amount
     * @return
     */
    OperationDto transfer(String recipientAccountNumber, String senderAccountNumber, BigDecimal amount);

    /**
     *
     * @param accountNumber
     * @param period
     * @return
     */
    ExtractDto makeExtract(String accountNumber, Period period);

    /**
     *
     * @param accountNumber
     * @param period
     * @return
     */
    MoneyStatementDto makeMoneyStatement(String accountNumber, Period period);
}

