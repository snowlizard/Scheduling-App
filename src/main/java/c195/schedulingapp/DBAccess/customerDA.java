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
 *
 * @author mrjack
 */
public class customerDA extends Connector{
    
    public customerDA(){}
    
    public ObservableList getCustomers(){
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String query = "SELECT * FROM customers";

        try{
            ResultSet set = connector.prepareStatement(query).executeQuery();
            while(set.next()){
                customers.add(new Customer(set.getInt("Customer_ID"), 
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
        return customers;
    }

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
 
    public void removeCustomer(int customer_id){
        String query = "DELETE FROM customers"
                + "WHERE Customer_ID = ?;";
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setInt(1, customer_id);
            pStatement.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}