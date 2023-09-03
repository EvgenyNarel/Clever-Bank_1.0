package org.narel.job;

import lombok.extern.slf4j.Slf4j;
import org.narel.dao.AccountDao;
import org.narel.dao.OperationDao;
import org.narel.entity.Account;
import org.narel.entity.Operation;
import org.narel.factory.ObjectFactory;
import org.narel.properties.PercentsProperties;
import org.narel.provider.impl.PercentsPropertiesProvider;
import org.narel.service.AccountService;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class PercentJob {

    private static final PercentsProperties percentsProperties = new PercentsPropertiesProvider().propertiesObject();

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public void accrueInterest() {
        executorService.scheduleAtFixedRate(new Task(), 5, 30, TimeUnit.SECONDS);
    }

    private static class Task implements Runnable {

        private final AccountDao accountDao = ObjectFactory.getObject(AccountDao.class);
        private final OperationDao operationDao = ObjectFactory.getObject(OperationDao.class);
        private final AccountService accountService = ObjectFactory.getObject(AccountService.class);

        @Override
        public void run() {
            try {
                Calendar calendar = Calendar.getInstance();
                if (calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMaximum(Calendar.DATE)) {
                    List<Account> accounts = accountDao.getAll();

                    for (Account account : accounts) {
                        if (isInterestCharged(account)) {
                            accountService.accrueInterest(account, percentsProperties.getCount());
                        }
                    }
                }
            } catch (Exception e) {
                log.error("Exception during accrual of interest to the user", e);
            }
        }

        private boolean isInterestCharged(Account account) {
            List<Operation> operations = Optional.of(account)
                    .map(Account::getId)
                    .map(operationDao::getByAccountId).stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toCollection(ArrayList::new));

            if (operations.isEmpty()) {
                return true;
            }

            operations.sort((o1, o2) -> o2.getOperationDate().compareTo(o1.getOperationDate()));

            return Duration.between(Instant.now(), operations.get(0).getOperationDate()).toHours() > 24;
        }
    }
}
