package com.ufu.vdata.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "commercial")
@Entity
public class Commercial {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "commercial_name", updatable = true, nullable = false)
    private String commercialName;
    @Column(name = "inn", updatable = true, nullable = false)
    private long inn;
    @Column(name = "address", updatable = true, nullable = false)
    private String address;
    @Column(name = "commercial_share", updatable = true, nullable = false)
    private BigDecimal commercialShare;
    @ManyToOne(optional = false)
    private Candidate candidate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCommercialShare() {
        return commercialShare;
    }

    public void setCommercialShare(BigDecimal commercialShare) {
        this.commercialShare = commercialShare;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
