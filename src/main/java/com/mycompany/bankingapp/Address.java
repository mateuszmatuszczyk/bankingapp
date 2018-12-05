package com.mycompany.bankingapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.Fetch;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import org.hibernate.annotations.FetchMode;

@Entity
@Table
@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Customer.class})
public class Address implements Serializable{
    
    public Address() {
        customers = new ArrayList<>();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int address_id;
    private String address_1;
    private String address_2;
    private String town;
    private String county;
    private String eircode;

    public Address(String address_1, String address_2, String town, String county, String eircode) {
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.town = town;
        this.county = county;
        this.eircode = eircode;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }
    
    
    @OneToMany(targetEntity=Customer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="address")
    @org.hibernate.annotations.Fetch(value = FetchMode.SUBSELECT)
    private List<Customer> customers;
    
    public void setCustomers(List<Customer> customerList) {
        this.customers = customerList;
    }
    
    
    @XmlElementRef()
    public List<Customer> getCustomers() {
        return customers;
    }
}
    
    
    
    
    
    
