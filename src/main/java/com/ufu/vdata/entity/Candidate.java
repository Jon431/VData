package com.ufu.vdata.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "candidate")
@Entity
public class Candidate {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "last_name", updatable = true, nullable = false, length = 50)
    private String lastName;
    @Column(name = "first_name", updatable = true, nullable = false, length = 50)
    private String firstName;
    @Column(name = "patronymic", updatable = true, nullable = false, length = 50)
    private String patronymic;
    @Column(name = "income_year", updatable = true, nullable = false)
    private short incomeYear;
    @Column(name = "estate_date", updatable = true, nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date estateDate;
    @Column(name = "document_type", updatable = true, nullable = true, length = 50)
    private String documentType;
    @Column(name = "document_number", updatable = true, nullable = false)
    private String documentNumber;
    @Column(name = "inn", updatable = true, nullable = false, length = 12)
    private String inn;
    @Column(name = "date_created", updatable = false, nullable = false)
    private Date dateCreated;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<Income> incomeList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<Request> requestList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<Sequrities> sequritiesList;
    @JoinColumn(name = "election_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Election election;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<Commercial> commercialList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<Money> moneyList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<Estate> estateList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<DocumentIn> documentInList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<Transport> transportList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<Stock> stockList;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate", orphanRemoval = true)
    private List<DocMonCom> docMonComList;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public short getIncomeYear() {
        return incomeYear;
    }

    public void setIncomeYear(short incomeYear) {
        this.incomeYear = incomeYear;
    }

    public Date getEstateDate() {
        return estateDate;
    }

    public void setEstateDate(Date estateDate) {
        this.estateDate = estateDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public List<Sequrities> getSequritiesList() {
        return sequritiesList;
    }

    public void setSequritiesList(List<Sequrities> sequritiesList) {
        this.sequritiesList = sequritiesList;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public List<Commercial> getCommercialList() {
        return commercialList;
    }

    public void setCommercialList(List<Commercial> commercialList) {
        this.commercialList = commercialList;
    }

    public List<Money> getMoneyList() {
        return moneyList;
    }

    public void setMoneyList(List<Money> moneyList) {
        this.moneyList = moneyList;
    }

    public List<Estate> getEstateList() {
        return estateList;
    }

    public void setEstateList(List<Estate> estateList) {
        this.estateList = estateList;
    }

    public List<DocumentIn> getDocumentInList() {
        return documentInList;
    }

    public void setDocumentInList(List<DocumentIn> documentInList) {
        this.documentInList = documentInList;
    }

    public List<Transport> getTransportList() {
        return transportList;
    }

    public void setTransportList(List<Transport> transportList) {
        this.transportList = transportList;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public List<DocMonCom> getDocMonComList() {
        return docMonComList;
    }

    public void setDocMonComList(List<DocMonCom> docMonComList) {
        this.docMonComList = docMonComList;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return id.equals(candidate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
