package org.narel.saver.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.narel.dao.AccountDao;
import org.narel.dao.BankDao;
import org.narel.entity.Account;
import org.narel.entity.Bank;
import org.narel.entity.enums.OperationKind;
import org.narel.factory.ObjectFactory;
import org.narel.generator.NumberGenerator;
import org.narel.model.Period;
import org.narel.model.dto.ExtractDto;
import org.narel.model.dto.MoneyStatementDto;
import org.narel.model.dto.OperationDto;
import org.narel.saver.CheckSaver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckSaverImpl implements CheckSaver {

    private static final Font RUS_FONT = getRusFont();

    private final AccountDao accountDao = ObjectFactory.getObject(AccountDao.class);
    private final BankDao bankDao = ObjectFactory.getObject(BankDao.class);
    private final NumberGenerator numberGenerator = ObjectFactory.getObject(NumberGenerator.class);

    @Override
    public void save(OperationDto operation) {
        Document document = new Document();
        try {
            long numberCheck = numberGenerator.getCheckNumber();
            createCheck(document, "check/", "check", numberCheck);
            document.open();

            Account account = accountDao.findById(operation.getRecipientId());
            Date date = Date.from(operation.getOperationDate());

            PdfPTable table = new PdfPTable(2);
            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            PdfPCell cell = new PdfPCell(new Paragraph("Банковский чек", RUS_FONT));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(cell);
            table.addCell(new Paragraph("Чек: ", RUS_FONT));
            table.addCell(new Paragraph(String.valueOf(numberCheck), RUS_FONT));
            table.addCell(new SimpleDateFormat("MM-dd-yyyy").format(date));
            table.addCell(new SimpleDateFormat("hh:mm:ss").format(date));
            table.addCell(new Paragraph("Тип транзакции: ", RUS_FONT));
            table.addCell(new Paragraph(String.valueOf(operation.getOperationKind()), RUS_FONT));
            table.addCell(new Paragraph("Банк отправителя: ", RUS_FONT));
            table.addCell(new Paragraph("-", RUS_FONT));
            table.addCell(new Paragraph("Банк получателя: ", RUS_FONT));
            table.addCell(new Paragraph(getBankName(account), RUS_FONT));
            table.addCell(new Paragraph("Счет отправителя: ", RUS_FONT));
            table.addCell(new Paragraph("-", RUS_FONT));
            table.addCell(new Paragraph("Счет получателя: ", RUS_FONT));
            table.addCell(new Paragraph(account.getAccountNumber(), RUS_FONT));
            table.addCell(new Paragraph("Сумма: ", RUS_FONT));
            table.addCell(operation.getAmount() + " " + operation.getCurrency());
            document.add(table);
        } catch (DocumentException | IOException e) {
            throw new IllegalStateException(e);
        } finally {
            document.close();
        }
    }

    @Override
    public void save(ExtractDto extract) {
        Date openingDate = Date.from(extract.getOpeningDate());
        Date extractFrom = Date.from(extract.getPeriod().from());
        Date extractTo = Date.from(extract.getPeriod().to());
        Date extractDate = Date.from(extract.getExtractDate());
        DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

        Document document = new Document();
        try {
            createCheck(document, "check/", "extract", numberGenerator.getExtractNumber());
            document.open();

            PdfPTable accountTable = new PdfPTable(2);
            accountTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            PdfPCell extractNameCell = new PdfPCell(new Paragraph("Выписка", RUS_FONT));
            extractNameCell.setBorder(PdfPCell.NO_BORDER);
            extractNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            extractNameCell.setColspan(2);
            PdfPCell bankNameCell = new PdfPCell(new Paragraph(extract.getBankName(), RUS_FONT));
            bankNameCell.setBorder(PdfPCell.NO_BORDER);
            bankNameCell.setColspan(2);
            bankNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell accountNumberCell = new PdfPCell(new Paragraph(extract.getAccountNumber()));
            accountNumberCell.setBorder(PdfPCell.ALIGN_TOP);
            PdfPCell currencyCell = new PdfPCell(new Paragraph(extract.getCurrency().toString()));
            currencyCell.setBorder(PdfPCell.ALIGN_TOP);
            PdfPCell openingDateCell = new PdfPCell(new Paragraph(dateFormatter.format(openingDate)));
            openingDateCell.setBorder(PdfPCell.ALIGN_TOP);
            PdfPCell periodCell = new PdfPCell(new Paragraph((dateFormatter.format(extractFrom) + " - " + dateFormatter.format(extractTo))));
            periodCell.setBorder(PdfPCell.ALIGN_TOP);
            PdfPCell extractDateCell = new PdfPCell(new Paragraph(dateFormatter.format(extractDate) + ", " + new SimpleDateFormat("hh.mm").format(extractDate)));
            extractDateCell.setBorder(PdfPCell.ALIGN_TOP);
            PdfPCell balanceCell = new PdfPCell(new Paragraph(String.valueOf(extract.getBalance())));
            balanceCell.setBorder(PdfPCell.ALIGN_TOP);

            accountTable.addCell(extractNameCell);
            accountTable.addCell(bankNameCell);
            accountTable.addCell(new Paragraph("Клиент", RUS_FONT));
            accountTable.addCell(extract.getOwnerFullName());
            accountTable.addCell(new Paragraph("Счет", RUS_FONT));
            accountTable.addCell(accountNumberCell);
            accountTable.addCell(new Paragraph("Валюта", RUS_FONT));
            accountTable.addCell(currencyCell);
            accountTable.addCell(new Paragraph("Дата открытия", RUS_FONT));
            accountTable.addCell(openingDateCell);
            accountTable.addCell(new Paragraph("Период", RUS_FONT));
            accountTable.addCell(periodCell);
            accountTable.addCell(new Paragraph("Дата и время формирования", RUS_FONT));
            accountTable.addCell(extractDateCell);
            accountTable.addCell(new Paragraph("Остаток", RUS_FONT));
            accountTable.addCell(balanceCell);

            PdfPTable tableOperations = new PdfPTable(3);
            accountTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            PdfPCell columDate = new PdfPCell(new Paragraph("Дата", RUS_FONT));
            columDate.setBorder(PdfPCell.CHUNK);
            columDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            columDate.setColspan(1);
            tableOperations.addCell(columDate);

            PdfPCell columnNotation = new PdfPCell(new Paragraph("Примечание", RUS_FONT));
            columnNotation.setBorder(PdfPCell.CHUNK);
            columnNotation.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnNotation.setColspan(1);
            tableOperations.addCell(columnNotation);

            PdfPCell columnAmount = new PdfPCell(new Paragraph("Сумма", RUS_FONT));
            columnAmount.setBorder(PdfPCell.CHUNK);
            columnAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnAmount.setColspan(1);
            tableOperations.addCell(columnAmount);

            document.add(accountTable);

            fillOperations(extract, extractFrom, extractTo, dateFormatter, tableOperations);

            document.add(tableOperations);
        } catch (DocumentException | IOException e) {
            throw new IllegalStateException(e);
        } finally {
            document.close();
        }
    }

    private static void fillOperations(ExtractDto extract, Date extractFrom, Date extractTo, DateFormat dateFormatter, PdfPTable operationsTable) {
        for (OperationDto operation : extract.getOperations()) {
            OperationKind operationKind = operation.getOperationKind();
            BigDecimal amount = operation.getAmount();
            if (operation.getOperationDate().isBefore(extractTo.toInstant())
                    && operation.getOperationDate().isAfter(extractFrom.toInstant())) {

                PdfPCell notationOperationCell = new PdfPCell(new Paragraph(operationKind.getKind(), RUS_FONT));
                notationOperationCell.setBorder(PdfPCell.ALIGN_TOP);
                notationOperationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                notationOperationCell.setColspan(1);

                PdfPCell amountOperationCell = new PdfPCell(new Paragraph(amount + " " + extract.getCurrency()));
                amountOperationCell.setBorder(PdfPCell.ALIGN_TOP);
                amountOperationCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                amountOperationCell.setColspan(1);

                PdfPCell dateOperationCell = new PdfPCell(new Paragraph(dateFormatter.format(Date.from(operation.getOperationDate()))));
                dateOperationCell.setBorder(PdfPCell.NO_BORDER);
                dateOperationCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                dateOperationCell.setColspan(1);

                operationsTable.addCell(dateOperationCell);
                operationsTable.addCell(notationOperationCell);
                operationsTable.addCell(amountOperationCell);
            }
        }
    }

    @Override
    public void save(MoneyStatementDto moneyStatement, Period period) {
        DateFormat dateFormatter = new SimpleDateFormat("MM.dd.yyyy");

        Document document = new Document();
        try {
            createCheck(document, "statement_money/", "moneyStatement", numberGenerator.getMoneyStatementNumber());

            document.open();

            PdfPCell MoneyStatementCell = new PdfPCell(new Paragraph("Money statement"));
            MoneyStatementCell.setBorder(PdfPCell.NO_BORDER);
            MoneyStatementCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            MoneyStatementCell.setColspan(2);

            PdfPCell bankNameCell = new PdfPCell(new Paragraph(moneyStatement.getBankName(), RUS_FONT));
            bankNameCell.setBorder(PdfPCell.NO_BORDER);
            bankNameCell.setColspan(2);
            bankNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell ownerCell = new PdfPCell(new Paragraph(moneyStatement.getFullName(), RUS_FONT));
            ownerCell.setBorder(PdfPCell.ALIGN_TOP);

            PdfPCell accountNumberCell = new PdfPCell(new Paragraph(moneyStatement.getAccountNumber()));
            accountNumberCell.setBorder(PdfPCell.ALIGN_TOP);

            PdfPCell currencyCell = new PdfPCell(new Paragraph(moneyStatement.getCurrency().toString()));
            currencyCell.setBorder(PdfPCell.ALIGN_TOP);

            PdfPCell openingDateCell = new PdfPCell(new Paragraph(dateFormatter.format(Date.from(moneyStatement.getOpeningDate()))));
            openingDateCell.setBorder(PdfPCell.ALIGN_TOP);

            PdfPCell periodCell = new PdfPCell(new Paragraph(((period.from())) + " - " + (period.to())));
            periodCell.setBorder(PdfPCell.ALIGN_TOP);

            PdfPCell moneyStatementDateCell = new PdfPCell(new Paragraph(dateFormatter.format(Date.from(Instant.now())) + ", " + new SimpleDateFormat("hh.mm").format(Date.from(Instant.now()))));
            moneyStatementDateCell.setBorder(PdfPCell.ALIGN_TOP);

            PdfPCell balanceCell = new PdfPCell(new Paragraph(String.valueOf(moneyStatement.getAmount())));
            balanceCell.setBorder(PdfPCell.ALIGN_TOP);

            PdfPTable accountTable = new PdfPTable(2);
            accountTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            accountTable.addCell(MoneyStatementCell);
            accountTable.addCell(bankNameCell);
            accountTable.addCell(new Paragraph("Клиент", RUS_FONT));
            accountTable.addCell(moneyStatement.getFullName());
            accountTable.addCell(new Paragraph("Счет", RUS_FONT));
            accountTable.addCell(accountNumberCell);
            accountTable.addCell(new Paragraph("Валюта", RUS_FONT));
            accountTable.addCell(currencyCell);
            accountTable.addCell(new Paragraph("Дата открытия", RUS_FONT));
            accountTable.addCell(openingDateCell);
            accountTable.addCell(new Paragraph("Период", RUS_FONT));
            accountTable.addCell(periodCell);
            accountTable.addCell(new Paragraph("Дата и время формирования", RUS_FONT));
            accountTable.addCell(moneyStatementDateCell);
            accountTable.addCell(new Paragraph("Остаток", RUS_FONT));
            accountTable.addCell(balanceCell);

            PdfPTable AccountActivity = new PdfPTable(2);
            accountTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            PdfPCell columIncome = new PdfPCell(new Paragraph("Приход", RUS_FONT));
            columIncome.setBorder(PdfPCell.CHUNK);
            columIncome.setHorizontalAlignment(Element.ALIGN_CENTER);
            columIncome.setColspan(1);
            AccountActivity.addCell(columIncome);

            PdfPCell columnExpense = new PdfPCell(new Paragraph("Уход", RUS_FONT));
            columnExpense.setBorder(PdfPCell.CHUNK);
            columnExpense.setHorizontalAlignment(Element.ALIGN_CENTER);
            columnExpense.setColspan(1);
            AccountActivity.addCell(columnExpense);

            PdfPCell incomeCell = new PdfPCell(new Paragraph(String.valueOf(moneyStatement.getIncome())));
            incomeCell.setBorder(PdfPCell.NO_BORDER);
            incomeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            incomeCell.setColspan(1);

            PdfPCell expenseCell = new PdfPCell(new Paragraph(String.valueOf(moneyStatement.getExpense())));
            expenseCell.setBorder(PdfPCell.ALIGN_TOP);
            expenseCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            expenseCell.setColspan(1);

            AccountActivity.addCell(incomeCell);
            AccountActivity.addCell(expenseCell);

            document.add(accountTable);
            document.add(AccountActivity);
        } catch (DocumentException | IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            document.close();
        }
    }

    @SneakyThrows
    private void createCheck(Document document, String dir, String file, long checkNumber) throws DocumentException, FileNotFoundException {
        Files.createDirectories(Paths.get(dir));
        PdfWriter.getInstance(document, new FileOutputStream(dir + file + checkNumber + ".pdf"));
    }

    @SneakyThrows
    private static Font getRusFont() {
        return new Font(BaseFont.createFont("fonts/FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
    }

    private String getBankName(Account account) {
        return Optional.of(account)
                .map(Account::getBankId)
                .map(bankDao::findById)
                .map(Bank::getBankName)
                .orElse(null);
    }

    private static class CheckSaverImplHandler {
        private final static CheckSaverImpl instance = new CheckSaverImpl();
    }

    public static CheckSaverImpl getInstance() {
        return CheckSaverImpl.CheckSaverImplHandler.instance;
    }
}
