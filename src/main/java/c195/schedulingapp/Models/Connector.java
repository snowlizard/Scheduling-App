/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import c195.schedulingapp.Singletons.Customers;
import c195.schedulingapp.Singletons.Divisions;
import c195.schedulingapp.Singletons.Countries;

/**
 *
 * @author mrjack
 */
public class Connector {
    private Connection connector;
    Customers customers = Customers.getInstance();
    Divisions divisions = Divisions.getInstance();
    Countries countries = Countries.getInstance();
    
    public Connector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connector = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/WGU",
                    "jgon", "Password@1");
            this.initCustomers();
            this.initDivisons();
            this.initCountries();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public Boolean validLogin(String userName, String password){
        Boolean found = false;
        String query = "SELECT * FROM Users WHERE User_Name = '" +
                userName + "' AND Password = '" + password + "';";
        
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                if(userName.equals(set.getString("User_Name")) 
                        && password.equals(set.getString("Password"))){
                    found = true;
                    customers.setLoggedInUser(userName);
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return found;
    }
    
    private void initCustomers(){
        customers.resetCustomers();
        String query = "SELECT * FROM Customers";
        
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                customers.addCustomer(new Customer(set.getInt("Customer_ID"), 
                        set.getString("Customer_name"),
                        set.getString("Address"), 
                        set.getString("Postal_Code"),
                        set.getString("Phone"), 
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Update_By"), 
                        set.getInt("Division_ID")));
            }
        }catch(Exception e){
            System.out.println(e + " Error");
        }
    }
    
    private void initDivisons(){
        divisions.resetDivisions();
        String query = "SELECT * FROM `First-Level Divisions`";
        
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                divisions.addDivision(new FirstLevelDivision(set.getInt("Division_ID"), 
                        set.getString("Division"),
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Updated_By"), 
                        set.getInt("Country_ID")));
            }
        }catch(Exception e){
            System.out.println(e + " Error");
        }
    }

    private void initCountries(){
        countries.resetCountries();
        String query = "SELECT * FROM Countries";
        
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                countries.addCountry(new Country(set.getInt("Country_ID"), 
                        set.getString("Country"),
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Updated_By")));
            }
        }catch(Exception e){
            System.out.println(e + " Error");
        }
    }
}
