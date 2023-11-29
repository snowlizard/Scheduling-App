/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Customers Singleton
 * @author Julian
 */
public class Customers {
    private static final Customers instance = new Customers();
    
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private Customer currentCustomer;
    private String loggedInUser;
    
    private Customers(){}
    
    /**
     * Gets customers instance
     * @return Customers
     */
    public static Customers getInstance(){
        return instance;
    }
    
    /**
     * List of all customers
     * @return ObersvableList<Customer>
     */
    public ObservableList<Customer> getCustomers(){
        return customers;
    }
    
    /**
     * Empties customer list
     */
    public void resetCustomers(){
        this.customers = FXCollections.observableArrayList();
    }
    
    /**
     * Adds customer to list
     * @param cust Customer
     */
    public void addCustomer(Customer cust){
        this.customers.add(cust);
    }
    
    /**
     * Removes a customer from the list
     * @param cust Customer
     */
    public void removeCustomer(Customer cust){
        this.customers.remove(cust);
    }
    
    /**
     * Gets a customer by their id
     * @param id integer
     * @return Customer
     */
    public Customer getCustomer(int id){
        Customer p = null;
        for(Customer cust: customers){
            if(cust.getId() == id){
                p = cust;
            }
        }
        
        return p;
    }
    
    /**
     * Gets the current customer
     * @return Customer
     */
    public Customer getCurrentCustomer(){
        return this.currentCustomer;
    }
    
    /**
     * Sets the current customer
     * @param cust Customer
     */
    public void setCurrentCustomer(Customer cust){
        currentCustomer = cust;
    }
    
    /**
     * Gets the name of the logged in user
     * @return String
     */
    public String getLoggedInUser(){
        return this.loggedInUser;
    }
    
    /**
     * Sets the logged in user
     * @param user String
     */
    public void setLoggedInUser(String user){
        loggedInUser = user;
    }
    
    /**
     * Gets the id of the customer by their name
     * @param name String
     * @return integer
     */
    public int getIdByName(String name){
        int id = -1;
        for(Customer customr: customers){
            if(customr.getName().equals(name)){
                id = customr.getId();
            }
        }
        return id;
    }
}
