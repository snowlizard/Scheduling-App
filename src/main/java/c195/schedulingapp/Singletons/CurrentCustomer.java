/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.Customer;
/**
 *
 * @author mrjack
 */
public class CurrentCustomer {
    private static final CurrentCustomer instance = new CurrentCustomer();
    private Customer customer;
    
    private CurrentCustomer(){}
    
    public static CurrentCustomer getInstance(){
        return instance;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public void setCustomer(Customer cust){
        this.customer = cust;
    }
}
