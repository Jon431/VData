package com.ufu.vdata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "sequrities")
@Entity
public class Sequrities {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "sequrities_type", updatable = true, nullable = false)
    private String sequritiesType;
    @Column(name = "debtor", updatable = true, nullable = false)
    private String debtor;
    @Column(name = "inn", updatable = true, nullable = false, length = 12)
    private String inn;
    @Column(name = "address", updatable = true, nullable = false)
    private String address;
    @Column(name = "amount", updatable = true, nullable = false)
    private BigDecimal amount;
    @Column(name = "sequrities_sum", updatable = true, nullable = false)
    private BigDecimal sequritiesSum;
    @JsonBackReference
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Candidate candidate;

    @JsonIgnore
    public boolean isValid() {
        return getSequritiesType()!=null && getDebtor()!=null && getInn()!=null && getAddress()!=null && getAmount()!=null && getSequritiesSum()!=null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSequritiesType() {
        return sequritiesType;
    }

    public void setSequritiesType(String sequritiesType) {
        this.sequritiesType = sequritiesType;
    }

    public String getDebtor() {
        return debtor;
    }

    public void setDebtor(String debtor) {
        this.debtor = debtor;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSequritiesSum() {
        return sequritiesSum;
    }

    public void setSequritiesSum(BigDecimal sequritiesSum) {
        this.sequritiesSum = sequritiesSum;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
