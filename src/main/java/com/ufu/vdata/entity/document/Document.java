package com.ufu.vdata.entity.document;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class Document {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "type")
    private byte type;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_sent", updatable = false)
    private Date dateSent;

    @Column(name = "date_answered", updatable = false)
    private Date dateAnswered;

    @Column(name = "status")
    private byte status;
        /*
            0 - invalid
            1 - created
            2 - sent
            3 - answer received, data is good
            4 - answer received, data is bad
         */

    @Column(name = "election_name", updatable = false, nullable = false)
    private String electionName;

    @Column(name = "candidate_first_name", updatable = false, nullable = false)
    private String candidateFirstName;

    @Column(name = "candidate_last_name", updatable = false, nullable = false)
    private String candidateLastName;

    @Column(name = "candidate_patronymic", updatable = false, nullable = false)
    private String candidatePatronymic;

    Document() {
        setStatus(Byte.parseByte("1"));
        setDateCreated(new Date());
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Date getDateAnswered() {
        return dateAnswered;
    }

    public void setDateAnswered(Date dateAnswered) {
        this.dateAnswered = dateAnswered;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getCandidateFirstName() {
        return candidateFirstName;
    }

    public void setCandidateFirstName(String candidateFirstName) {
        this.candidateFirstName = candidateFirstName;
    }

    public String getCandidateLastName() {
        return candidateLastName;
    }

    public void setCandidateLastName(String candidateLastName) {
        this.candidateLastName = candidateLastName;
    }

    public String getCandidatePatronymic() {
        return candidatePatronymic;
    }

    public void setCandidatePatronymic(String candidatePatronymic) {
        this.candidatePatronymic = candidatePatronymic;
    }
}
