/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author mrjack
 */
public class Customers {
    private static final Customers instance = new Customers();
    
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private Customer currentCustomer;
    private String loggedInUser;
    
    private Customers(){}
    
    public static Customers getInstance(){
        return instance;
    }
    
    public ObservableList<Customer> getCustomers(){
        return customers;
    }
    
    public void resetCustomers(){
        this.customers = FXCollections.observableArrayList();
    }
    
    public void addCustomer(Customer cust){
        this.customers.add(cust);
    }
    
    public void removeCustomer(Customer cust){
        this.customers.remove(cust);
    }
    
    public Customer getCustomer(int id){
        Customer p = null;
        for(Customer cust: customers){
            if(cust.getId() == id){
                p = cust;
            }
        }
        
        return p;
    }
    
    public Customer getCurrentCustomer(){
        return this.currentCustomer;
    }
    
    public void setCurrentCustomer(Customer cust){
        currentCustomer = cust;
    }
    
    public String getLoggedInUser(){
        return this.loggedInUser;
    }
    
    public void setLoggedInUser(String user){
        loggedInUser = user;
    }
    
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
