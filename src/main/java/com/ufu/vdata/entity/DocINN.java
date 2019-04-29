package com.ufu.vdata.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "document_INN")
@Entity
public class DocINN extends Document {
    private String hhhhernyyyyya2;

    public String getHhhhernyyyyya2() {
        return hhhhernyyyyya2;
    }

    public void setHhhhernyyyyya2(String hhhhernyyyyya2) {
        this.hhhhernyyyyya2 = hhhhernyyyyya2;
    }

    public DocINN() {
        super();
        setType(Byte.parseByte("2"));
    }
}
