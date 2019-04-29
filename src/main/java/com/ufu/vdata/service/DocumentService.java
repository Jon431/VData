package com.ufu.vdata.service;

import com.ufu.vdata.entity.DocMonCom;
import com.ufu.vdata.entity.Document;
import com.ufu.vdata.repository.DocMonComListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    private final DocMonComListRepository docMonComListRepository;

    @Autowired
    public DocumentService(DocMonComListRepository docMonComListRepository) {
        this.docMonComListRepository = docMonComListRepository;
    }
    public List<Document> getAllByType(Byte type) {
        return new ArrayList<>(docMonComListRepository.getAllByType(type));
    }
    public void populate() {
        DocMonCom ddd = new DocMonCom();
        ddd.setCandidateFirstName("firstname");
        ddd.setCandidateLastName("lastname");
        ddd.setCandidatePatronymic("patronymic");
        ddd.setHuinya("hernya");
        ddd.setElectionName("electionnnnnn");
        ddd.setType(Byte.parseByte("1"));
        docMonComListRepository.save(ddd);
        DocMonCom dd = new DocMonCom();
        dd.setCandidateFirstName("dfgdfgfdg");
        dd.setCandidateLastName("hnhnyn");
        dd.setCandidatePatronymic("cvcvcv");
        dd.setHuinya("12345");
        dd.setElectionName("electiofffnnnnnn");
        dd.setType(Byte.parseByte("2"));
        docMonComListRepository.save(dd);
    }
}
