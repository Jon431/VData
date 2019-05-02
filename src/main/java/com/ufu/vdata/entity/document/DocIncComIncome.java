package com.ufu.vdata.entity.document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "document_income")
@Entity
public class DocIncComIncome {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonBackReference
    @JoinColumn(name = "document_income_commercial_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DocIncCom docIncCom;

    @Column(name = "income_source")
    private String incomeSource;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "is_matches")
    private boolean isMatches;

    @Column(name = "data")
    @Length(max = 100)
    private String data;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DocIncCom getDocIncCom() {
        return docIncCom;
    }

    public void setDocIncCom(DocIncCom docIncCom) {
        this.docIncCom = docIncCom;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
