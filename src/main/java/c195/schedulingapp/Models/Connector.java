/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import c195.schedulingapp.Singletons.Appointments;
import c195.schedulingapp.Singletons.Contacts;
import c195.schedulingapp.Singletons.Customers;
import c195.schedulingapp.Singletons.Countries;
import c195.schedulingapp.Singletons.Divisions;
import c195.schedulingapp.Singletons.Users;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Used for SQL connection
 * @author Julian
 */
public class Connector {
    private Connection connector;
    Appointments appointments = Appointments.getInstance();
    Contacts  contacts  = Contacts.getInstance();
    Customers customers = Customers.getInstance();
    Countries countries = Countries.getInstance();
    Divisions divisions = Divisions.getInstance();
    Users users = Users.getInstance();
    
    HelperFunctions helper = new HelperFunctions();
    
    public Connector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connector = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/WGU",
                    "jgon", "Password@1");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void initAll(){
        this.initAppointments();
        this.initContacts();
        this.initCustomers();
        this.initCountries();
        this.initDivisons();
        this.initUsers();
    }
    
    /**
     * Checks for valid login
     * @param userName String
     * @param password String
     * @return true if valid
     */
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
    
    /**
     * Queries the SQL data base for customers and populates the data for
     * use in the application
     */
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
    
    /**
     * Queries the SQL data base for Divisions and populates the data for
     * use in the application
     */
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
    
    /**
     * Queries the SQL data base for Countries and populates the data for
     * use in the application
     */
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
    
    /**
     * Queries the SQL data base for Contacts and populates the data for
     * use in the application
     */
    private void initContacts(){
        contacts.resetContacts();
        String query = "SELECT * FROM Contacts";
        
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                contacts.addContact(new Contact(
                         set.getInt("Contact_ID"),
                        set.getString("Contact_Name"),
                       set.getString("Email")));
            }
        }catch(Exception e){
            System.out.println(e );
        }
    }
    
    /**
     * Queries the SQL data base for Appointments and populates the data for
     * use in the application
     */
    private void initAppointments(){
        appointments.resetAppointments();
        String query = "SELECT * FROM Appointments";
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                String startStr = set.getString("Start");
                String endStr   = set.getString("End");
                String createStr= set.getString("Create_Date");
                String lastUpStr= set.getString("Last_Update");
                
                appointments.addAppointment(new Appointment(
                                set.getInt("Appointment_ID"),
                                set.getString("Title"),
                                set.getString("Description"),
                                set.getString("Location"),
                                set.getString("Type"),
                                helper.getZDTFromUTC(startStr),
                                helper.getZDTFromUTC(endStr),
                                helper.getZDT(createStr),
                                set.getString("Create_By"),
                                helper.getZDT(lastUpStr),
                                set.getString("Last_Updated_By"),
                                set.getInt("Customer_ID"),
                                set.getInt("User_ID"),
                                set.getInt("Contact_ID")));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /**
     * Queries the SQL data base for Users and populates the data for
     * use in the application
     */
    private void initUsers(){
        users.resetUsers();
        String query = "SELECT * FROM Users";
        
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                users.addUser(new User(
                         set.getInt("User_ID"),
                        set.getString("User_Name")));
            }
        }catch(Exception e){
            System.out.println(e );
        }
    }
    
    public ObservableList<CountryReport> getCountriesReport(){
        ObservableList<CountryReport> countryReport = FXCollections.observableArrayList();
        
        String query = "select Country, Division, " +
        "SUM(CASE WHEN Customers.Division_ID = `First-Level Divisions`.Division_ID THEN 1 " +
        "WHEN `First-Level Divisions`.Country_ID = Countries.Country_ID THEN 1 END) AS  Total " +
        "from Customers " +
        "LEFT JOIN `First-Level Divisions` ON Customers.Division_ID = `First-Level Divisions`.Division_ID " +
        "LEFT JOIN Countries ON `First-Level Divisions`.Country_ID = Countries.Country_ID GROUP BY Customers.Division_ID;";
        
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                countryReport.add(new CountryReport(
                        set.getString("Country"),
                        set.getString("Division"),
                        set.getInt("Total")
                ));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        return countryReport;
    }
}
