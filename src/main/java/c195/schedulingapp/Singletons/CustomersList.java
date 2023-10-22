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
public class CustomersList {
    private static final CustomersList instance = new CustomersList();
    
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    
    private CustomersList(){}
    
    public static CustomersList getInstance(){
        return instance;
    }
    
    public ObservableList<Customer> getCustomers(){
        return customers;
    }
    
    public void addCustomer(Customer cust){
        this.customers.add(cust);
    }
}
