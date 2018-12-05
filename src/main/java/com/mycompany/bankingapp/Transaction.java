package com.mycompany.bankingapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table
@XmlRootElement
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int trans_id;
    private double trans_value;
    private Date trans_datetime;
    private String trans_type;
    private String trans_description;

    public Transaction(double trans_value, Date trans_datetime, String trans_type, String trans_description) {
        this.trans_value = trans_value;
        this.trans_datetime = trans_datetime;
        this.trans_type = trans_type;
        this.trans_description = trans_description;
    }

    public Transaction() {
    }

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public double getTrans_value() {
        return trans_value;
    }

    public void setTrans_value(double trans_value) {
        this.trans_value = trans_value;
    }

    public Date getTrans_datetime() {
        return trans_datetime;
    }

    public void setTrans_datetime(Date trans_datetime) {
        this.trans_datetime = trans_datetime;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getTrans_description() {
        return trans_description;
    }

    public void setTrans_description(String trans_description) {
        this.trans_description = trans_description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    @XmlTransient
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
