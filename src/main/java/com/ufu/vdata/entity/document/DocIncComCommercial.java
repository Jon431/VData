package com.ufu.vdata.entity.document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "document_commercial")
@Entity
public class DocIncComCommercial {
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

    @Column(name = "commercial_name")
    private String commercialName;

    @Column(name = "inn")
    private String inn;

    @Column(name = "address")
    private String address;

    @Column(name = "commercial_share")
    private BigDecimal commercialShare;

    @Column(name = "is_matches")
    private boolean isMatches;

    @Column(name = "note")
    @Length(max = 100)
    private String note;

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

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
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

    public BigDecimal getCommercialShare() {
        return commercialShare;
    }

    public void setCommercialShare(BigDecimal commercialShare) {
        this.commercialShare = commercialShare;
    }

    public boolean isMatches() {
        return isMatches;
    }

    public void setMatches(boolean matches) {
        isMatches = matches;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
