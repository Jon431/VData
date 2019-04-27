package com.ufu.vdata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@MappedSuperclass
public class Document {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonBackReference
    @JoinColumn(name = "candidate", referencedColumnName = "id")
    @ManyToOne
    private Candidate candidate;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_sent", updatable = false, nullable = false)
    private Date dateSent;

    @Column(name = "date_answered", updatable = false, nullable = false)
    private Date dateAnswered;

    @Column(name = "status", updatable = true)
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


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
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
