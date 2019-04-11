package com.ufu.vdata.service.parser;

import com.ufu.vdata.entity.Candidate;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class InputFileProcessor {

private ClassicFormDocx classicFormDocx;
private Candidate candidate;
private XWPFDocument document;
    public Candidate createCandidateFromFile(MultipartFile uploadedFile) {
        try {
            String filename = uploadedFile.getOriginalFilename();
            boolean isDocx = filename.endsWith(".docx");
            boolean isDoc = filename.endsWith(".doc");
            boolean isClassicFormDocx = false;

            if (isDoc) throw new UnsupportedOperationException(); // no Doc support for now

            if (isDocx) {   //check structure of the document
                document = new XWPFDocument(uploadedFile.getInputStream());
                XWPFTable table = document.getTables().get(0);
                String fio = (table.getRow(0).getCell(0).getText());
                isClassicFormDocx = (fio.equals("Фамилия,\nимя,\nотчество")); }

            if (isClassicFormDocx) { candidate =  classicFormDocx.processClassicDocx(document); }
            else throw new UnsupportedOperationException();
        }
        catch (IOException ex) {
            System.out.println("IOException");
        }



        return candidate;
    }

    @Autowired
    public InputFileProcessor(ClassicFormDocx classicFormDocx) {
        this.classicFormDocx = classicFormDocx;
    }
}
