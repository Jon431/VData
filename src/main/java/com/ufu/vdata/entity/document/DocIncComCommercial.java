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
}
