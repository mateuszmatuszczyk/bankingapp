/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankingapp;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author x14737355
 */
public class BankingAppMain {

    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

//        public Customer(String first_name, String middle_name, String last_name, String email, Date dob, String phone_number, int reg_number, int pac) {
//        public Address(String address_1, String address_2, String town, String county, String eircode) {
        Address address1 = new Address("20 Seabury Park", "Seabury", "Malahide", "Co. Dublin", "X36K380");
        Address address2 = new Address("11 Sonesta Green", "Millview", "Swords", "Co. Dublin", "Z62Q340");

        entitymanager.persist(address1);
        entitymanager.persist(address2);

        Customer customer1 = new Customer("James", "Maria", "Rodriguez", "james69@gmail.com", Date.valueOf("1990-02-22"), "0896969690", 339189, 179931);
        Customer customer2 = new Customer("Jurgen", "Anatol", "Klopp", "jurgi@gmail.com", Date.valueOf("1969-12-05"), "0835118448", 775632, 163478);

        customer1.setAddress(address1);
        customer2.setAddress(address2);

        entitymanager.persist(customer1);
        entitymanager.persist(customer2);

        Account account1 = new Account("IENaNBOFI900017621235", "Current Account", Date.valueOf("1999-12-22"), null, 344.23, 2.1, true);
        Account account2 = new Account("IENaNBOFI900017478237", "Savings Account", Date.valueOf("1999-12-22"), null, 24.23, 5.1, true);
        Account account3 = new Account("IENaNBOFI900017465927", "Current Account", Date.valueOf("2010-01-12"), null, 24.23, 5.1, true);

        account1.setCustomer(customer1);
        account2.setCustomer(customer1);
        account3.setCustomer(customer2);

        Branch branch = new Branch("1", "Main Street", "Arklow", "Co. Wicklow", "Y14IE3", "0402382900");
        entitymanager.persist(branch);
        
        account1.setBranch(branch);
        account2.setBranch(branch);
        account3.setBranch(branch);
        
        entitymanager.persist(account1);
        entitymanager.persist(account2);
        entitymanager.persist(account3);
 

        Transaction trans1 = new Transaction(20.6, new java.sql.Date(Calendar.getInstance().getTime().getTime()), "Credit", "Transaction description bla bla bla");
        Transaction trans2 = new Transaction(665.6, new java.sql.Date(Calendar.getInstance().getTime().getTime()), "Debit", "Transaction description bla bla bla");
        Transaction trans3 = new Transaction(15.6, new java.sql.Date(Calendar.getInstance().getTime().getTime()), "Credit", "Transaction description bla bla bla");
        Transaction trans4 = new Transaction(55.6, new java.sql.Date(Calendar.getInstance().getTime().getTime()), "Credit", "Transaction description bla bla bla");
        Transaction trans5 = new Transaction(10.1, new java.sql.Date(Calendar.getInstance().getTime().getTime()), "Debit", "Transaction description bla bla bla");
        Transaction trans6 = new Transaction(155.2, new java.sql.Date(Calendar.getInstance().getTime().getTime()), "Credit", "Transaction description bla bla bla");

        trans1.setAccount(account1);
        trans2.setAccount(account1);
        trans3.setAccount(account2);
        trans4.setAccount(account2);
        trans5.setAccount(account3);
        trans6.setAccount(account3);

        ArrayList<Transaction> translist1 = new ArrayList<>();
        translist1.add(trans1);
        translist1.add(trans2);

        ArrayList<Transaction> translist2 = new ArrayList<>();
        translist2.add(trans3);
        translist2.add(trans4);

        ArrayList<Transaction> translist3 = new ArrayList<>();
        translist3.add(trans5);
        translist3.add(trans6);

        account1.setTransactions(translist1);
        entitymanager.persist(account1);

        account2.setTransactions(translist2);
        entitymanager.persist(account2);

        account3.setTransactions(translist3);
        entitymanager.persist(account3);

        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();
    }
}
