package com.ufu.vdata.entity.document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.Income;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "document_income_commercial")
@Entity
public class DocIncCom extends Document{

    @JsonBackReference
    @JoinColumn(name = "candidate", referencedColumnName = "id")
    @ManyToOne
    private Candidate candidate;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "document_number")
    private String documentNumber;
    @Column
    private String inn;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docIncCom", orphanRemoval = true)
    private List<DocIncComIncome> incomes;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docIncCom", orphanRemoval = true)
    private List<DocIncComCommercial> commercials;


    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public DocIncCom() {
        super();
        setType(Byte.parseByte("1"));
    }
    public DocIncCom(Candidate cnd) {
        super();
        setType(Byte.parseByte("1"));
        setCandidate(cnd);
        setCandidateFirstName(cnd.getFirstName());
        setCandidateLastName(cnd.getLastName());
        setCandidatePatronymic(cnd.getPatronymic());
        setElectionName(cnd.getElection().toString());
        setDocumentType(cnd.getDocumentType());
        setDocumentNumber(cnd.getDocumentNumber());
        setInn(cnd.getInn()); /*
        ArrayList<DocIncComIncome> docIncomes = new ArrayList<>();
        for (Income cndIncome : cnd.getIncomeList()) {
            DocIncComIncome docIncome = new DocIncComIncome();
            docIncome.setIncomeSource(cndIncome.getIncomeSource());
            docIncome.setAmount(cndIncome.getAmount());
            incomes.add(docIncome);
        }
        setIncomes(docIncomes);
*/ //TODO this does not work now
    }


    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public List<DocIncComIncome> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<DocIncComIncome> incomes) {
        this.incomes = incomes;
    }

    public List<DocIncComCommercial> getCommercials() {
        return commercials;
    }

    public void setCommercials(List<DocIncComCommercial> commercials) {
        this.commercials = commercials;
    }


}
