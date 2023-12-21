/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import c195.schedulingapp.Models.CountryReport;
import c195.schedulingapp.Models.TMReport;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mrjack
 */
public class reportsDA extends Connector{
    public reportsDA(){}

    public ObservableList<CountryReport> getCountriesReport(){
        ObservableList<CountryReport> countryReport = FXCollections.observableArrayList();
        
        String query = "select Country, Division, " +
        "SUM(CASE WHEN customers.Division_ID = first_level_divisions.Division_ID THEN 1 " +
        "WHEN first_level_divisions.Country_ID = countries.Country_ID THEN 1 END) AS  Total " +
        "from customers " +
        "LEFT JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID " +
        "LEFT JOIN countries ON first_level_divisions.Country_ID = countries.Country_ID GROUP BY customers.Division_ID;";
        
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

    public ObservableList<TMReport> getTMReport(){
        ObservableList<TMReport> tmReport = FXCollections.observableArrayList();
        
        String query = "SELECT MONTHNAME(Start) AS Month, Type, SUM(CASE WHEN appointments.Customer_ID = customers.Customer_ID THEN 1 END) AS Total FROM appointments LEFT JOIN customers on appointments.Customer_ID = customers.Customer_ID GROUP BY MONTHNAME(Start), Type;";
        
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                tmReport.add(new TMReport(
                        set.getString("Month"),
                        set.getString("Type"),
                        set.getInt("Total")
                ));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return tmReport;
    }
}
