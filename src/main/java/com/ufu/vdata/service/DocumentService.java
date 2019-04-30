package com.ufu.vdata.service;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.document.DocINN;
import com.ufu.vdata.entity.document.DocIncCom;
import com.ufu.vdata.entity.document.DocIncComIncome;
import com.ufu.vdata.entity.document.Document;
import com.ufu.vdata.repository.DocINNListRepository;
import com.ufu.vdata.repository.DocIncComRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DocumentService {
    private final DocIncComRepository docIncComRepository;
    private final DocINNListRepository docINNListRepository;

    @Autowired
    public DocumentService(DocIncComRepository docIncComRepository, DocINNListRepository docINNListRepository) {
        this.docIncComRepository = docIncComRepository;
        this.docINNListRepository = docINNListRepository;
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

    public Document createDocIncCom(Candidate cnd) {
        return null;//TODO
    }


}
