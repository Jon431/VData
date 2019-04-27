package com.ufu.vdata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "document_money_commercial")
@Entity
public class DocMonCom extends Document{




    private String huinya;

    public String getHuinya() {
        return huinya;
    }

    public void setHuinya(String huinya) {
        this.huinya = huinya;
    }


}
