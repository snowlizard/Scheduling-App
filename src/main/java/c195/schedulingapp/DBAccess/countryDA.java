/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import c195.schedulingapp.Models.Country;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Country database access class
 * @author Julian
 */
public class countryDA extends Connector{
    public countryDA(){}

    /**
     * Returns a list of all countries
     * @return ObservableList<Country>
     */
    public ObservableList<Country> getCountries(){
        ObservableList<Country> paizes = FXCollections.observableArrayList();
        String query = "SELECT * FROM countries;";
        
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                paizes.add(new Country(set.getInt("Country_ID"), 
                        set.getString("Country"),
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Updated_By")));
            }
        }catch(Exception e){
            System.out.println(e + " Error");
        }
        return paizes;
    }
    
    /**
     * Returns the country with
     * the given id
     * @param id int
     * @return Country
     */
    public Country getById(int id){
        Country country = null;
        String query = "SELECT * FROM countries WHERE Country_ID = ?;";
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setInt(1, id);
            ResultSet set = pStatement.executeQuery();
            while(set.next()){
                country = new Country(set.getInt("Country_ID"), 
                        set.getString("Country"),
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Updated_By"));
            }
        }catch(Exception e){
            System.out.println(e + " Error");
        }
        return country;  
    }
    
    /**
     * Get a country by its name
     * @param name String
     * @return Country
     */
    public Country getByName(String name){
        Country country = null;
        String query = "SELECT * FROM countries WHERE Country = ?;";
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setString(1, name);
            ResultSet set = pStatement.executeQuery();
            while(set.next()){
                country = new Country(set.getInt("Country_ID"), 
                        set.getString("Country"),
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Updated_By"));
            }
        }catch(Exception e){
            System.out.println(e + " Error");
        }
        return country;  
    }
}
