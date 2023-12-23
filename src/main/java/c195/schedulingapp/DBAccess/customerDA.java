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
    private static Customer currentCustomer;
    
    public customerDA(){}
    
    public void setCurrentCustomer(Customer customerName){
        currentCustomer = customerName;
    }
    
    public Customer getCurrentCustomer(){
        return currentCustomer;
    }
    
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
        String query = "delete from customers where Customer_ID = ?;";
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setInt(1, customer_id);
            pStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public int getIdByName(String name){
        int id = -1;
        String query = "SELECT Customer_ID from customers"
                + "WHERE Customer_Name = ?;";
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
