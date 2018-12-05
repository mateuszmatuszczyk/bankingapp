package com.mycompany.bankingapp;

import java.io.Serializable;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table
@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Account.class})

public class Customer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cust_id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String email;    
    
    private Date dob;
    
    private String phone_number;
    
    private int reg_number;
    private int pac; //personal access code
    
           
    public Customer(){
        accounts = new ArrayList<>();         
    }

    public Customer(String first_name, String middle_name, String last_name, String email, Date dob, String phone_number, int reg_number, int pac) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.email = email;
        this.dob = dob;
        this.phone_number = phone_number;
        this.reg_number = reg_number;
        this.pac = pac;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getReg_number() {
        return reg_number;
    }

    public void setReg_number(int reg_number) {
        this.reg_number = reg_number;
    }

    public int getPac() {
        return pac;
    }

    public void setPac(int pac) {
        this.pac = pac;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    @XmlTransient
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    @OneToMany(targetEntity=Account.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="customer")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Account> accounts;
    
    public void setAccounts(List<Account> accountlist) {
        this.accounts = accountlist;
    }
    
    @XmlElementWrapper(name="accounts")
    @XmlElementRef()
    public List<Account> getAccounts() {
        return accounts;
    }
}