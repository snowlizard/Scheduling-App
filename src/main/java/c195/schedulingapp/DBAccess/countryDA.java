/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import c195.schedulingapp.Models.Country;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mrjack
 */
public class countryDA extends Connector{
    public countryDA(){}

    public ObservableList<Country> getCountries(){
        ObservableList<Country> paizes = FXCollections.observableArrayList();
        String query = "SELECT * FROM countries";
        
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
}
