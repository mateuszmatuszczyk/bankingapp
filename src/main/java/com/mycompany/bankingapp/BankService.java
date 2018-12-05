package com.mycompany.bankingapp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bank")
public class BankService {
    
    EntityManager entityManager;
    
    public BankService(){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("test-connection");
        entityManager = emfactory.createEntityManager();
    }
    @GET
    @Produces({MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
    @Path("/customers/{cust_id}")
    public Customer getCustomer(@PathParam("cust_id") int cust_id) {
        Customer customer = entityManager.find(Customer.class, cust_id);
        System.out.println(customer);
        return customer;
    }
    
    @POST
    @Path("/customers/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newCustomer(Customer customer) {
//        System.out.println(c);
        entityManager.getTransaction().begin();

        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        
        entityManager.close();
//        entityManager.close();

        return Response.status(200).entity(customer).build();
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
    @Path("customers/{cust_id}/address")
    public Address getCustAddress(@PathParam("cust_id") int cust_id) {
        Customer customer = entityManager.find(Customer.class, cust_id);
        Address x = customer.getAddress();
        System.out.println(x);
        //Address cust_address = customer.getAddress();
        return customer.getAddress();
        
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
    @Path("accounts/{account_id}/transactions")
    public List<Transaction> getAccountTransactions(@PathParam("account_id") int account_id) {
        Account account = entityManager.find(Account.class, account_id);

        //Address cust_address = customer.getAddress();
        return account.getTransactions();
        
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
    @Path("customer/{cust_id}/accounts/")
    public List<Account> getAccounts(@PathParam("cust_id") int cust_id) {
        Customer customer = entityManager.find(Customer.class, cust_id);
        return customer.getAccounts();
        
    }
}