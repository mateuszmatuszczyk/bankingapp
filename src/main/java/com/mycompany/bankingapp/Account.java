package com.mycompany.bankingapp;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table
@XmlRootElement
@XmlSeeAlso({Transaction.class})

public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int account_id;
    private String IBAN;
    private String acccount_name;
    private Date start_date;
    private Date end_date;
    private double balance;
    private double interest_rate;
    private boolean status;

    public Account(String IBAN, String acccount_name, Date start_date, Date end_date, double balance, double interest_rate, boolean status) {
        this.IBAN = IBAN;
        this.acccount_name = acccount_name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.balance = balance;
        this.interest_rate = interest_rate;
        this.status = status;
    }
    public Account(){
        transactions = new ArrayList<>();         
    }
    
    @OneToMany(targetEntity=Transaction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="account")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Transaction> transactions;
    
    public void setTransactions(List<Transaction> transactionlist) {
        this.transactions = transactionlist;
    }
    
    @XmlElementWrapper(name="transactions")
    @XmlElementRef()
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    
    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getAcccount_name() {
        return acccount_name;
    }

    public void setAcccount_name(String acccount_name) {
        this.acccount_name = acccount_name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cust_id")
    private Customer customer;
    @XmlTransient
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @XmlTransient
    public Branch getBranch() {
        return branch;
    }
    
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
