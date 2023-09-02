package org.narel.saver.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.narel.model.dto.ExtractDto;
import org.narel.model.dto.MoneyStatementDto;
import org.narel.model.dto.OperationDto;
import org.narel.saver.CheckSaver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckSaverImpl implements CheckSaver {

    @Override
    public void save(OperationDto operation) {

    }

    @Override
    public void save(ExtractDto extract) {

    }

    @Override
    public void save(MoneyStatementDto moneyStatement) {

    }

    private static class CheckSaverImplHandler {
        private final static CheckSaverImpl instance = new CheckSaverImpl();
    }

    public static CheckSaverImpl getInstance() {
        return CheckSaverImpl.CheckSaverImplHandler.instance;
    }
}
