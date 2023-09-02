package org.narel.dao;

import org.narel.entity.Account;
import org.narel.entity.MoneyStatement;
import org.narel.model.Period;

import java.util.List;
import java.util.UUID;
/**
 *
 */
public interface AccountDao extends EntityDao<Account> {
    /**
     *
     * @param ownerId
     * @return
     */
    List<Account> getAccountsByOwnerId(UUID ownerId);
    /**
     *
     * @param id
     * @param period
     * @return
     */
    MoneyStatement getMoneyStatement(UUID id, Period period);

    /**
     *
     * @param accountNumber
     * @return
     */
    Account getAccountByAccountNumber(String accountNumber);
}