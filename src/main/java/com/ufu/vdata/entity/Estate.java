package com.ufu.vdata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "estate")
@Entity
public class Estate {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "estate_type", updatable = true, nullable = false)
    private String estateType;
    @Column(name = "address", updatable = true, nullable = false)
    private String address;
    @Column(name = "estate_space", updatable = true, nullable = false)
    private BigDecimal estateSpace;
    @JsonBackReference
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Candidate candidate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEstateType() {
        return estateType;
    }

    public void setEstateType(String estateType) {
        this.estateType = estateType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getEstateSpace() {
        return estateSpace;
    }

    public void setEstateSpace(BigDecimal estateSpace) {
        this.estateSpace = estateSpace;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
