package com.ufu.vdata.service;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.document.*;
import com.ufu.vdata.repository.CandidateListRepository;
import com.ufu.vdata.repository.DocINNListRepository;
import com.ufu.vdata.repository.DocIncComRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Document> getAllByTypeAndStatus(Byte type, Byte status) {
        List<Document> result = docIncComRepository.getAllByTypeAndStatus(type, status);
        result.addAll(docINNListRepository.getAllByTypeAndStatus(type, status));
        return result;
    }

    public List<Document> getAllByDateCreated(Date date1, Date date2){
        List<Document> result = docIncComRepository.getAllByDateCreatedBetween(date1,date2);
        result.addAll(docINNListRepository.getAllByDateCreatedBetween(date1,date2));
        return result;
    }

    public void createDocIncComs(List<String> cndIds) {
        for (String id : cndIds) {
            Candidate cnd = candidateListRepository.findById(UUID.fromString(id)).orElseThrow(() -> new IllegalArgumentException("Not found candidate with ID: " + id));
            docIncComRepository.save(new DocIncCom(cnd));
        }
        docIncComRepository.flush();
    }

    public void createDocINNs(List<String> cndIds) {
        for (String id : cndIds) {
            Candidate cnd = candidateListRepository.findById(UUID.fromString(id)).orElseThrow(() -> new IllegalArgumentException("Not found candidate with ID: " + id));
            docINNListRepository.save(new DocINN(cnd));
        }
        docINNListRepository.flush();
    }

    public void sendDocuments(List<String> docIds) {
        for (String docId : docIds) {
            Optional<DocIncCom> docIncCom = docIncComRepository.findById(UUID.fromString(docId));
            if(docIncCom.isPresent()) {
                docIncCom.get().setStatus(Byte.parseByte("2"));
                docIncCom.get().setDateSent(new Date());
                docIncComRepository.save(docIncCom.get());
            }
            else {
                Optional<DocINN> docINN = docINNListRepository.findById(UUID.fromString(docId));
                if (docINN.isPresent()) {
                    docINN.get().setStatus(Byte.parseByte("2"));
                    docINN.get().setDateSent(new Date());
                    docINNListRepository.save(docINN.get());
                }
                else { throw new IllegalArgumentException("Not found document with ID: " + docId); }
            }
        }
    }


}
