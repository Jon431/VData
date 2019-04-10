package com.ufu.vdata.service.parser;

import com.ufu.vdata.entity.Candidate;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

@Service
class ClassicFormDocx {
    Candidate cnd;
public    Candidate processClassicDocx(XWPFDocument docx) {
    return new Candidate();
    }
}
