package com.ufu.vdata.service.parser;

import com.ufu.vdata.entity.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
class ClassicFormDocx {
    Candidate cnd;

    Candidate processClassicDocx(XWPFDocument docx) {

    cnd = new Candidate();

    List<XWPFTableRow> rows =  docx.getTables().get(0).getRows();


    cnd.setEstateDate(parseEstateDate(rows.get(0)).getTime());

    cnd.setIncomeYear(parseIncomeYear(rows.get(2)));

    String[] name = parseName(rows.get(7));
    cnd.setLastName(name[0]);
    cnd.setFirstName(name[1]);
    cnd.setPatronymic(name[2]);

    String[] docInn = parseDocumentAndINN(rows.get(7));
    cnd.setDocumentNumber(docInn[0]);
    cnd.setInn(docInn[1]);

    List<Income> cndInc = new ArrayList<>();
    cnd.setIncomeList(cndInc);
    for(int i = 7; i < rows.size(); i++) {
        cndInc.add(parseIncome(rows.get(i)));
    }

    List<Estate> cndEst = new ArrayList<>();
    cnd.setEstateList(cndEst);
    for(int i = 7; i < rows.size(); i++) {
        for(int j = 3; j <= 8; j++) {
            cndEst.add(parseEstate(rows.get(i), j));
        }
    }

    List<Transport> cndTra = new ArrayList<>();
    cnd.setTransportList(cndTra);
    for(int i = 7; i < rows.size(); i++) {
        cndTra.add(parseTransport(rows.get(i)));
    }

    List<Money> cndMon = new ArrayList<>();
    cnd.setMoneyList(cndMon);
    for(int i = 7; i < rows.size(); i++) {
        cndMon.add(parseMoney(rows.get(i)));
    }
    List<Stock> cndStk = new ArrayList<>();
    cnd.setStockList(cndStk);
    for(int i = 7; i < rows.size(); i++) {
        cndStk.add(parseStock(rows.get(i)));
    }

    List<Sequrities> cndSeq = new ArrayList<>();
    cnd.setSequritiesList(cndSeq);
    for(int i = 7; i < rows.size(); i++) {
        cndSeq.add(parseSequrities(rows.get(i)));
    }

    List<Commercial> cndCom = new ArrayList<>();
    cnd.setCommercialList(cndCom);
    for(int i = 7; i < rows.size(); i++) {
        cndCom.add(parseCommercial(rows.get(i)));
    }



    return cnd;
    }


    private Calendar parseEstateDate(XWPFTableRow row) {
        int day = Integer.parseInt(row.getCell(4).getText().replaceAll("[^0-9]", ""));
        String monthString = row.getCell(6).getText();
        int month;
        if (monthString.matches(".*\\d.*")) {
            month = Integer.parseInt(monthString.replaceAll("[^0-9]", ""))-1;}
        else {
            String m = monthString.toLowerCase().replaceAll("[^а-я]", "");
            if (m.startsWith("янв")) { month = 0;}
            else if (m.startsWith("фев")) {month = 1;}
            else if (m.startsWith("мар")) {month = 2;}
            else if (m.startsWith("апр")) {month = 3;}
            else if (m.startsWith("ма"))  {month = 4;}
            else if (m.startsWith("июн")) {month = 5;}
            else if (m.startsWith("июл")) {month = 6;}
            else if (m.startsWith("авг")) {month = 7;}
            else if (m.startsWith("сен")) {month = 8;}
            else if (m.startsWith("окт")) {month = 9;}
            else if (m.startsWith("ноя")) {month = 10;}
            else if (m.startsWith("дек")) {month = 11;}
            else throw new IllegalArgumentException();
        }
        int year = Integer.parseInt(row.getCell(8).getText().replaceAll("[^0-9]", "")) + 2000;
        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day);
        return cal;
    }
    private short parseIncomeYear(XWPFTableRow row) {
        return Short.parseShort(row.getCell(3).getText().replaceAll("[^0-9]",""));
    }

    private String[] parseName(XWPFTableRow row) {
        return row.getCell(0).getText().split("\\s|,\\s|,", 3);
    }

    private String[] parseDocumentAndINN(XWPFTableRow row) {
        Pattern pattern = Pattern.compile("^(.*\\d).*(\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d).*$");
        Matcher matcher = pattern.matcher(row.getCell(1).getText());
        String[] data = new String[2];
        if (matcher.find()) {
        data[0] = matcher.group(1);
        data[1] = matcher.group(2); }
        return data;
    }

    private Income parseIncome(XWPFTableRow row) {
        Income income = new Income();
        Pattern pattern = Pattern.compile("^(.*[А-Яа-я0-9Ёё])(, |,| )(\\d\\d\\d\\d+\\.\\d\\d|\\d\\d\\d\\d+,\\d\\d|\\d\\d\\d\\d+).*$");
        Matcher matcher = pattern.matcher(row.getCell(2).getText());
        if (matcher.find()) {
            income.setIncomeSource(matcher.group(1));
            income.setAmount(new BigDecimal(matcher.group(3).replace(",", ".")));
        }
        return income;
    }

    private Estate parseEstate(XWPFTableRow row, int pos) {
        Estate estate = new Estate();
        Pattern pattern = Pattern.compile("^(.*[А-Яа-я0-9Ёё])(, |,| )(\\d+\\.\\d+|\\d+,\\d+|\\d+).*$");
        Matcher matcher = pattern.matcher(row.getCell(pos).getText());
        if (matcher.find()) {
            estate.setAddress(matcher.group(1));
            estate.setEstateSpace((new BigDecimal(matcher.group(3).replace(",", "."))));
            switch(pos) {
                case 3: {
                    estate.setEstateType("Земельный участок");
                    break;
                }
                case 4: {
                    estate.setEstateType("Жилой дом");
                    break;
                }
                case 5: {
                    estate.setEstateType("Квартира");
                    break;
                }
                case 6: {
                    estate.setEstateType("Дача");
                    break;
                }
                case 7: {
                    estate.setEstateType("Гараж");
                    break;
                }
                case 8: {
                    estate.setEstateType("Иное недвижимое имущество");
                    break;
                }
            }

        }
        return estate;
    }

    private Transport parseTransport(XWPFTableRow row) {
        Transport transport = new Transport();
        Pattern pattern = Pattern.compile("^([A-Za-zА-Яа-я- Ёё]+)(, |,| )([A-Za-zА-Яа-яЁё0-9-]+)(, |,| )([A-Za-zА-Яа-яЁё0-9-]+)(, |,| )(\\d\\d\\d\\d)$");
        Matcher matcher = pattern.matcher(row.getCell(9).getText());
        if (matcher.find()) {
            transport.setTransportType(matcher.group(1));
            transport.setBrand((matcher.group(3)));
            transport.setModel(matcher.group(5));
            transport.setTrasportYear(Short.parseShort(matcher.group(7)));
        }
        return transport;
    }

    private Money parseMoney(XWPFTableRow row) {
        Money money = new Money();
        Pattern pattern = Pattern.compile("^([A-Za-zА-Яа-яЁё0-9-.\"«» ]{4,20})(, |,| )([A-Za-zА-Яа-яЁё0-9-,. ]{8,40}[A-Za-zА-Яа-яЁё0-9])(, |,| )([0-9- ]{6,40})(, |,| )([0-9 ]{2,12},[0-9]{1,2}|[0-9 ]{2,12}\\.[0-9]{1,2}|[0-9 ]{2,12})$");
        Matcher matcher = pattern.matcher(clean(row.getCell(10).getText()));
        if (matcher.find()) {
            money.setBankName(matcher.group(1));
            money.setBankAddress(matcher.group(3));
            money.setBankAccountNumber(matcher.group(5));
            money.setBankBalance(new BigDecimal(matcher.group(7).replace(",", ".")));
        }
        return money;
    }

    private Stock parseStock(XWPFTableRow row) {
        Stock stock = new Stock();
        Pattern pattern = Pattern.compile("([A-Za-zА-Яа-я0-9-.\"«» Ёё]{4,20})(, |,| )([0-9- ]{12})(, |,| )([A-Za-zА-Яа-я0-9-,. Ёё]{8,40}[A-Za-zА-Яа-яЁё0-9])(, |,| )([0-9 ]{2,12},[0-9]{1,2}|[0-9 ]{2,12}\\.[0-9]{1,2}|[0-9 ]{2,12})(, |,| )([0-9 ]{2,12},[0-9]{1,2}|[0-9 ]{2,12}\\.[0-9]{1,2}|[0-9 ]{2,12}[0-9])");
        Matcher matcher = pattern.matcher(clean(row.getCell(11).getText()));
        if (matcher.find()) {
            stock.setCompanyName(matcher.group(1));
            stock.setInn(matcher.group(3));
            stock.setAddress(matcher.group(5));
            stock.setAmount(new BigDecimal(matcher.group(7).replace(",", ".")));
            stock.setPrice(new BigDecimal(matcher.group(9).replace(",", ".")));
        }
        return stock;
    }
    private String clean(String dirty) {
        return dirty.replaceAll("[^A-Za-zА-Я а-я0-9.,\"«»Ёё]", "").replaceAll("  ", " ").replaceAll(" ,", ",");
    }

    private Sequrities parseSequrities(XWPFTableRow row) {
        Sequrities sequrities = new Sequrities();
        Pattern pattern = Pattern.compile("([A-Za-zА-Яа-я- Ёё]{4,20})(, |,| )([A-Za-zА-Яа-я- Ёё]{4,20})(, |,| )([0-9- ]{12})(, |,| )([A-Za-zА-Яа-я0-9-,. Ёё]{8,40}[A-Za-zА-Яа-я0-9Ёё])(, | )([0-9]{1,10},[0-9]{1,10}|[0-9]{1,10}\\.[0-9]{1,10}|[0-9]{1,10})(, | )([0-9 ]{1,12},[0-9]{2}|[0-9 ]{1,12}\\.[0-9]{2}|[0-9 ]{2,12}[0-9])([^0-9]*$)");
        Matcher matcher = pattern.matcher(clean(row.getCell(12).getText()));
        if (matcher.find()) {
            sequrities.setSequritiesType(matcher.group(1));
            sequrities.setDebtor(matcher.group(3));
            sequrities.setInn(matcher.group(5));
            sequrities.setAddress(matcher.group(7));
            sequrities.setAmount(new BigDecimal(matcher.group(9).replace(",", ".")));
            sequrities.setSequritiesSum(new BigDecimal(matcher.group(11).replace(",", ".")));
        }
        return sequrities;
    }

    private Commercial parseCommercial(XWPFTableRow row) {
        Commercial commercial = new Commercial();
        Pattern pattern = Pattern.compile("([A-Za-zА-Яа-я0-9-,. Ёё\"«»]{4,20}[A-Za-zА-Яа-я0-9Ёё\"»])(, |,| )([0-9- ]{12})(, |,| )([A-Za-zА-Яа-я0-9-,. Ёё\"«»]{6,50}[A-Za-zА-Яа-яЁё0-9])(, |,| )([0-9]{1,2}.[0-9]{1,10}|[0-9]{1,2},[0-9]{1,10}|[0-9]{1,2})([^0-9]*$)");
        Matcher matcher = pattern.matcher(clean(row.getCell(13).getText()));
        if (matcher.find()) {
            commercial.setCommercialName(matcher.group(1));
            commercial.setInn(matcher.group(3));
            commercial.setAddress(matcher.group(5));
            commercial.setCommercialShare(new BigDecimal((matcher.group(7).replace(",","."))));
        }
        return commercial;
    }
}
