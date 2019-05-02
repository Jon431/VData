package com.ufu.vdata.entity.document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ufu.vdata.entity.Candidate;

import javax.persistence.*;

@Table(name = "document_INN")
@Entity
public class DocINN extends Document {
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
    @Column(name = "is_matches")
    private boolean isMatches;
    @Column
    private String data;

    public DocINN() {
        super();
        setType(Byte.parseByte("2"));
    }
    public DocINN(Candidate cnd) {
        super();
        setType(Byte.parseByte("2"));
        setElectionName(cnd.getElection().toString());
        setCandidateFirstName(cnd.getFirstName());
        setCandidateLastName(cnd.getLastName());
        setCandidatePatronymic(cnd.getPatronymic());
        setCandidate(cnd);
        setDocumentType(cnd.getDocumentType());
        setDocumentNumber(cnd.getDocumentNumber());
        setInn(cnd.getInn());
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
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

    public boolean isMatches() {
        return isMatches;
    }

    public void setMatches(boolean matches) {
        isMatches = matches;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
