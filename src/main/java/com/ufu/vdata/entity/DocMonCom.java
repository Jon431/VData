package com.ufu.vdata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "document_money_commercial")
@Entity
public class DocMonCom extends Document{

    @JsonBackReference
    @JoinColumn(name = "candidate", referencedColumnName = "id")
    @ManyToOne
    private Candidate candidate;


    private String huinya;

    public String getHuinya() {
        return huinya;
    }

    public void setHuinya(String huinya) {
        this.huinya = huinya;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public DocMonCom() {
        setType(Byte.parseByte("1"));
        setDateCreated(new Date());
        setStatus(Byte.parseByte("1"));
    }
}
