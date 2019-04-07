package com.ufu.vdata.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name = "request")
@Entity
public class Request {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "request_type", updatable = true, nullable = false)
    private Short requestType;
    @Column(name = "request_date", updatable = true, nullable = false)
    private Date requestDate;
    @Column(name = "status", updatable = true, nullable = false)
    private Short status;
    @Column(name = "file_out_link", updatable = true, nullable = false)
    private String fileOutLink;
    @Column(name = "file_in_link", updatable = true, nullable = false)
    private String fileInLink;
    @JoinColumn(name = "candidate", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Candidate candidate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Short getRequestType() {
        return requestType;
    }

    public void setRequestType(Short requestType) {
        this.requestType = requestType;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getFileOutLink() {
        return fileOutLink;
    }

    public void setFileOutLink(String fileOutLink) {
        this.fileOutLink = fileOutLink;
    }

    public String getFileInLink() {
        return fileInLink;
    }

    public void setFileInLink(String fileInLink) {
        this.fileInLink = fileInLink;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
