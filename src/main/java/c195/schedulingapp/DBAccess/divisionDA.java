/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import c195.schedulingapp.Models.FirstLevelDivision;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mrjack
 */
public class divisionDA extends Connector{
    public divisionDA(){}

    public ObservableList<FirstLevelDivision> getDivisions(){
        ObservableList<FirstLevelDivision> firstDivs = FXCollections.observableArrayList();
        String query = "SELECT * FROM first_level_divisions;";
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                firstDivs.add(new FirstLevelDivision(set.getInt("Division_ID"), 
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
        return firstDivs;
    }
    
    public FirstLevelDivision getById(int id){
        FirstLevelDivision division = null;
        String query = "select * from first_level_divisions where Division_ID = ?;";
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setInt(1, id);
            ResultSet set = pStatement.executeQuery();
            if(set.next()){
                return new FirstLevelDivision(set.getInt("Division_ID"), 
                        set.getString("Division"),
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Updated_By"), 
                        set.getInt("Country_ID"));
            }
        }catch(Exception e){
            System.out.println(e + " Error");
        }
        return division;
    }
    
    public FirstLevelDivision getByName(String name){
        FirstLevelDivision division = null;
        String query = "select*from first_level_divisions where Division = ?;";
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setString(1, name);
            ResultSet set = pStatement.executeQuery();
            if(set.next()){
                division = new FirstLevelDivision(set.getInt("Division_ID"), 
                        set.getString("Division"),
                        set.getString("Create_Date"),
                        set.getString("Created_By"), 
                        set.getString("Last_Update"),
                        set.getString("Last_Updated_By"), 
                        set.getInt("Country_ID"));
            }
        }catch(Exception e){
            System.out.println(e + " Error");
        }
        return division;
    }
}
