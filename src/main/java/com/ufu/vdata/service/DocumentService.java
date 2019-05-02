package com.ufu.vdata.service;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.Commercial;
import com.ufu.vdata.entity.Income;
import com.ufu.vdata.entity.document.*;
import com.ufu.vdata.repository.CandidateListRepository;
import com.ufu.vdata.repository.DocINNListRepository;
import com.ufu.vdata.repository.DocIncComRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {
    private final DocIncComRepository docIncComRepository;
    private final DocINNListRepository docINNListRepository;
    private final CandidateListRepository candidateListRepository;

    @Autowired
    public DocumentService(DocIncComRepository docIncComRepository, DocINNListRepository docINNListRepository, CandidateListRepository candidateListRepository) {
        this.docIncComRepository = docIncComRepository;
        this.docINNListRepository = docINNListRepository;
        this.candidateListRepository = candidateListRepository;
    }

    public List<Document> getAll() {
        List<Document> result = docIncComRepository.getAll();
        result.addAll(docINNListRepository.getAll());
        return result;
    }

    public List<Document> getAllByType(Byte type) {
        switch (type) {
            case 1: {return new ArrayList<>(docIncComRepository.findAll());}
            case 2: {return new ArrayList<>(docINNListRepository.findAll());}
        }
        return new ArrayList<>();
    }

    public List<Document> getAllByStatus(Byte status) {

        List<Document> result = docIncComRepository.getAllByStatus(status);
        result.addAll(docINNListRepository.getAllByStatus(status));
        return result;
    }

    public List<Document> getAllByDateCreated(Date date1, Date date2){
        List<Document> result = docIncComRepository.getAllByDateCreatedBetween(date1,date2);
        result.addAll(docINNListRepository.getAllByDateCreatedBetween(date1,date2));
        return result;
    }

    public void createDocIncComs(List<String> cndIds) {
        for (String id : cndIds) {
            Candidate cnd = candidateListRepository.findById(UUID.fromString(id)).orElseThrow(() -> new IllegalArgumentException("Not found candidate " + id));
            DocIncCom dnc = new DocIncCom();
            dnc.setCandidate(cnd);
            dnc.setCandidateFirstName(cnd.getFirstName());
            dnc.setCandidateLastName(cnd.getLastName());
            dnc.setCandidatePatronymic(cnd.getPatronymic());
            dnc.setElectionName(cnd.getElection().toString());
            dnc.setDocumentType(cnd.getDocumentType());
            dnc.setDocumentNumber(cnd.getDocumentNumber());
            dnc.setInn(cnd.getInn());
            ArrayList<DocIncComIncome> docIncomes = new ArrayList<>();
            for (Income cndIncome : cnd.getIncomeList()) {
                DocIncComIncome docIncome = new DocIncComIncome();
                docIncome.setIncomeSource(cndIncome.getIncomeSource());
                docIncome.setAmount(cndIncome.getAmount());
                docIncome.setDocIncCom(dnc);
                docIncomes.add(docIncome);
            }
            dnc.setIncomes(docIncomes);
            ArrayList<DocIncComCommercial> docComs = new ArrayList<>();
            for (Commercial cndCom : cnd.getCommercialList()) {
                DocIncComCommercial docCom = new DocIncComCommercial();
                docCom.setDocIncCom(dnc);
                docCom.setCommercialName(cndCom.getCommercialName());
                docCom.setInn(cndCom.getInn());
                docCom.setAddress(cndCom.getAddress());
                docCom.setCommercialShare(cndCom.getCommercialShare());
                docComs.add(docCom);
            }
            dnc.setCommercials(docComs);
            docIncComRepository.save(dnc);
        }
        docIncComRepository.flush();
    }


}
