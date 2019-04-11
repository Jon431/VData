package com.ufu.vdata.service.parser;

import com.ufu.vdata.entity.Candidate;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
class ClassicFormDocx {
    Candidate cnd;
Candidate processClassicDocx(XWPFDocument docx) {

    cnd = new Candidate();

    List<XWPFTableRow> rows = new ArrayList<>();
    XWPFTable table = docx.getTables().get(0);

    XWPFTableRow row0 = table.getRow(0);



    cnd.setEstateDate(parseEstateDate(row0).getTime());


    //String t = row0.getCell(6).getText() + " " + row0.getCell(8).getText() + "20" + row0.getCell(10).getText();




    //for(int i = 7; i < table.getNumberOfRows(); i++) {
    //    rows.add(table.getRow(i));
    //}





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
}
