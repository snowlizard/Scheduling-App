/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import c195.schedulingapp.Models.Customer;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Customer database access class
 * @author Julian
 */
public class customerDA extends Connector{
    private static Customer currentCustomer;
    
    public customerDA(){}
    
    /**
     * Sets the current customer
     * @param customerName Customer
     */
    public void setCurrentCustomer(Customer customerName){
        currentCustomer = customerName;
    }
    
    /**
     * Return the current customer
     * @return Customer
     */
    public Customer getCurrentCustomer(){
        return currentCustomer;
    }
    
    /**
     * Return a list of all customers
     * @return ObservableList<Customer>
     */
    public ObservableList<Customer> getCustomers(){
        ObservableList<Customer> custs = FXCollections.observableArrayList();
        String query = "SELECT * FROM customers";

        try{
            ResultSet set = connector.prepareStatement(query).executeQuery();
            while(set.next()){
                custs.add(new Customer(set.getInt("Customer_ID"), 
                        set.getString("Customer_name"),
                        set.getString("Address"), 
                        set.getString("Postal_Code"),
                        set.getString("Phone"), 
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Updated_By"), 
                        set.getInt("Division_ID")));
                }
            }catch(Exception e){
                System.out.println(e + " Error");
            }
        return custs;
    }

    /**
     * Add new customer to the database
     * @param cust Customer
     */
    public void insertCustomer(Customer cust){
        String query = "insert into customers "
                + "values(?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setNull(1, java.sql.Types.INTEGER);
            pStatement.setString(2, cust.getName());
            pStatement.setString(3, cust.getAddress());
            pStatement.setString(4, cust.getPostalCode());
            pStatement.setString(5, cust.getPhone());
            pStatement.setString(6, helper.getUTCfromLocalString(cust.getCreateDate()));
            pStatement.setString(7, cust.getCreatedBy());
            pStatement.setString(8 , helper.getUTCfromLocalString(cust.getLastUpdate()));
            pStatement.setString(9, cust.getLastUpdatedBy());
            pStatement.setInt(10, cust.getDivisionId());

            pStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /**
     * Update an existing customer in
     * the database
     * @param cust Customer 
     */
    public void updateCustomer(Customer cust){
        String query = "UPDATE customers "
                + "SET Customer_Name = ?, "
                + "Address = ?, "
                + "Postal_Code = ?, "
                + "Phone = ?, "
                + "Create_Date = ?, "
                + "Created_By = ?, "
                + "Last_update = ?, "
                + "Last_Updated_By = ?, "
                + "Division_ID = ? "
                + "WHERE Customer_ID = ?;";

        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setString(1, cust.getName());
            pStatement.setString(2, cust.getAddress());
            pStatement.setString(3, cust.getPostalCode());
            pStatement.setString(4, cust.getPhone());
            pStatement.setString(5, helper.getUTCfromLocalString(cust.getCreateDate()));
            pStatement.setString(6, cust.getCreatedBy());
            pStatement.setString(7 , helper.getUTCfromLocalString(cust.getLastUpdate()));
            pStatement.setString(8, cust.getLastUpdatedBy());
            pStatement.setInt(9, cust.getDivisionId());
            pStatement.setInt(10, cust.getId());

            pStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    } 
 
    /**
     * Remove a customer from the
     * database
     * @param customer_id int
     */
    public void removeCustomer(int customer_id){
        String query = "delete from customers where Customer_ID = ?;";
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setInt(1, customer_id);
            pStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /**
     * Get the id of a customer by their name
     * @param name String
     * @return int
     */
    public int getIdByName(String name){
        int id = -1;
        String query = "select Customer_ID from customers where Customer_Name = ?;";
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setString(1, name);
            ResultSet set = pStatement.executeQuery();
            if(set.next()){
                id = set.getInt("Customer_ID");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return id;
    }
    
    /**
     * Get a customer by their id
     * @param id int
     * @return Customer
     */
    public Customer getCustomer(int id){
        Customer cust = null;
        String query = "select * from customers where Customer_ID = ?;";
        
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setInt(1, id);
            ResultSet set = pStatement.executeQuery();
            if(set.next()){
                cust = new Customer(set.getInt("Customer_ID"), 
                        set.getString("Customer_name"),
                        set.getString("Address"), 
                        set.getString("Postal_Code"),
                        set.getString("Phone"), 
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Updated_By"), 
                        set.getInt("Division_ID"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return cust;
    }
}
