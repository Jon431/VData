package com.ufu.vdata.service;

import com.ufu.vdata.entity.DocINN;
import com.ufu.vdata.entity.DocMonCom;
import com.ufu.vdata.entity.Document;
import com.ufu.vdata.repository.DocINNListRepository;
import com.ufu.vdata.repository.DocMonComListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {
    private final DocMonComListRepository docMonComListRepository;
    private final DocINNListRepository docINNListRepository;

    @Autowired
    public DocumentService(DocMonComListRepository docMonComListRepository, DocINNListRepository docINNListRepository) {
        this.docMonComListRepository = docMonComListRepository;
        this.docINNListRepository = docINNListRepository;
    }
    public List<Document> getAllByType(Byte type) {
        switch (type) {
            case 1: {return new ArrayList<>(docMonComListRepository.findAll());}
            case 2: {return new ArrayList<>(docINNListRepository.findAll());}
            case 0: {
                List<Document> result = docMonComListRepository.getAll();
                result.addAll(docINNListRepository.getAll());
                return result;
            }
        }
        return new ArrayList<>();
    }

    public List<Document> getAllByStatus(Byte status) {

        List<Document> result = docMonComListRepository.getAllByStatus(status);
        result.addAll(docINNListRepository.getAllByStatus(status));
        return result;

    }

    public void populate() {
        DocMonCom ddd = new DocMonCom();
        ddd.setCandidateFirstName("firstname");
        ddd.setCandidateLastName("lastname");
        ddd.setCandidatePatronymic("patronymic");
        ddd.setHuinya("hernya");
        ddd.setElectionName("electionnnnnn");
        docMonComListRepository.save(ddd);
        DocMonCom dd = new DocMonCom();
        dd.setCandidateFirstName("dfgdfgfdg");
        dd.setCandidateLastName("hnhnyn");
        dd.setCandidatePatronymic("cvcvcv");
        dd.setHuinya("12345");
        dd.setElectionName("electiofffnnnnnn");
        docMonComListRepository.save(dd);
        DocINN dnn = new DocINN();
        dnn.setCandidateFirstName("fbfb");
        dnn.setCandidateLastName("lastnaf2me");
        dnn.setCandidatePatronymic("patrony2mic");
        dnn.setHhhhernyyyyya2("he2rnya");
        dnn.setElectionName("electionnnnn2n");
        docINNListRepository.save(dnn);
    }
}
