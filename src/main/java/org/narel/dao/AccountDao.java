package org.narel.dao;

import org.narel.dao.EntityDao;
import org.narel.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountDao extends EntityDao<Account> {

    List<Account> getAccountsByOwnerId(UUID ownerId);
}