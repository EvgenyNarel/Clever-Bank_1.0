//package org.narel.service;
//
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.narel.dao.AccountDao;
//import org.narel.dao.BankDao;
//import org.narel.dao.OperationDao;
//import org.narel.entity.Operation;
//import org.narel.entity.enums.Currency;
//import org.narel.entity.enums.OperationKind;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class CheckService {
//    OperationDao operationDao;
//    AccountDao accountDao;
//    BankDao bankDao;
//    private int counterNumberCheck = 0;
//
//    void formCheck(Operation operation) {
//        accountDao.findById(operation.getSenderId()).getAccountNumber();
//        Instant operationDate = operation.getOperationDate();
//        Date date = Date.from(operationDate);
//        DateFormat formatterDate = new SimpleDateFormat("MM-dd-yyyy");
//        String operationDateFormatter = formatterDate.format(date);
//        DateFormat formatterTime = new SimpleDateFormat("hh:mm:ss");
//        String operationTimeFormatter = formatterTime.format(date);
//        OperationKind operationKind = operation.getOperationKind();
//        String bankNameRecipient = bankDao.findById(operation.getRecipientId()).getBankName();
//        String bankNameSender = bankDao.findById(operation.getSenderId()).getBankName();
//        String accountNumberSender = accountDao.findById(operation.getSenderId()).getAccountNumber();
//        String accountNumberRecipient = accountDao.findById(operation.getRecipientId()).getAccountNumber();
//        BigDecimal amount = operation.getAmount();
//
//        Document document = new Document();
//        try {
//            BaseFont bf_russian = BaseFont.createFont(
//                    "font/FreeSans.ttf",
//                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            Font russian = new Font(bf_russian, 12);
//            int numberCheck = counterNumberCheck + 1;
//            PdfWriter.getInstance(document, new FileOutputStream("check/check" + numberCheck + ".pdf"));
//            document.open();
//            PdfPTable table = new PdfPTable(2);
//            table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
//            PdfPCell cell = new PdfPCell(new Paragraph("���������� ���", russian));
//            cell.setBorder(PdfPCell.NO_BORDER);
//            Paragraph numberCheckCell = new Paragraph(String.valueOf(numberCheck), russian);
//            Paragraph operationKindCell = new Paragraph(String.valueOf(operationKind), russian);
//            Paragraph bankNameSenderCell = new Paragraph(String.valueOf(bankNameSender), russian);
//            Paragraph bankNameRecipientCell = new Paragraph(String.valueOf(bankNameRecipient), russian);
//            Paragraph accountNumberSenderCell = new Paragraph(String.valueOf(accountNumberSender), russian);
//            Paragraph accountNumberRecipientCell = new Paragraph(String.valueOf(accountNumberRecipient), russian);
//
//            cell.setColspan(2);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(cell);
//            table.addCell(new Paragraph("���", russian));
//            table.addCell(numberCheckCell);
//            table.addCell(operationDateFormatter);
//            table.addCell(operationTimeFormatter);
//            table.addCell(new Paragraph("��� ����������: ", russian));
//            table.addCell(operationKindCell);
//            table.addCell(new Paragraph("���� �����������: ", russian));
//            table.addCell(bankNameSenderCell);
//            table.addCell(new Paragraph("���� ����������: ", russian));
//            table.addCell(bankNameRecipientCell);
//            table.addCell(new Paragraph("���� �����������: ", russian));
//            table.addCell(accountNumberSenderCell);
//            table.addCell(new Paragraph("���� ����������: ", russian));
//            table.addCell(accountNumberRecipientCell);
//            table.addCell(new Paragraph("�����: ", russian));
//            table.addCell(String.valueOf(amount));
//            document.add(table);
//        } catch (DocumentException | FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            document.close();
//        }
//
//    }
//
//    void formExtract(CreditDebitDto creditDebitDto) {
//        Instant instantFrom = creditDebitDto.getPeriod().from();
//        Instant instantTo = creditDebitDto.getPeriod().to();
//
//        ArrayList<Operation> operationGivenPeriod = new ArrayList<>();
//
//        List<Operation> userOperations = creditDebitDto.getUserOperations();
//        for (Operation operation : userOperations) {
//            Instant operationDate = operation.getOperationDate();
//            Timestamp operationDateTimestamp = Timestamp.from(operationDate);
//            if (operationDateTimestamp.before(Timestamp.from(instantFrom)) && operationDateTimestamp.after(Timestamp.from(instantTo))) {
//                operationGivenPeriod.add(operation);
//            }
//        }
//        String fullName = creditDebitDto.getFullName();
//        String accountNumber = creditDebitDto.getAccount().getAccountNumber();
//        Currency currency = creditDebitDto.getCurrency();
//        Instant openingDate = creditDebitDto.getOpeningDate();
//        Instant thisOperationDate = creditDebitDto.getOperationDate();
//        BigDecimal accountBalance = creditDebitDto.getAccountBalance();
//        for (Operation operation : operationGivenPeriod) {
//            Instant operationDate = operation.getOperationDate();
//            OperationKind operationKind = operation.getOperationKind();
//            BigDecimal amount = operation.getAmount();
//
//            /**��������� ��������� �� � pdf ��� � �������� ����� ���������� ���������� � pdf**/
//        }
//
//    }
//
//    public void formCreditDebit(CreditDebitDto creditDebitUser) {
//        String bankName = creditDebitUser.getBankName();
//        String fullName = creditDebitUser.getFullName();
//        String accountNumber = creditDebitUser.getAccount().getAccountNumber();
//        Currency currency = creditDebitUser.getCurrency();
//        Instant openingDate = creditDebitUser.getOpeningDate();
//        Instant from = creditDebitUser.getPeriod().from();
//        Instant instant = creditDebitUser.getPeriod().to();
//        Instant operationDate = creditDebitUser.getOperationDate();
//        BigDecimal accountBalance = creditDebitUser.getAccountBalance();
//        BigDecimal expense = creditDebitUser.getExpense();
//        BigDecimal income = creditDebitUser.getIncome();
//
//
//    }
//}