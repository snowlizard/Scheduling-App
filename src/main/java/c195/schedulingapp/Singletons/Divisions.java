/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mrjack
 */
public class Divisions {
    private static final Divisions instance = new Divisions();
    
    private ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();
    
    private Divisions(){}
    private FirstLevelDivision division;
    
    public static Divisions getInstance(){
        return instance;
    }
    
    public ObservableList<FirstLevelDivision> getDivisions(){
        return divisions;
    }
    
    public void resetDivisions(){
        this.divisions = FXCollections.observableArrayList();
    }
    
    public void addDivision(FirstLevelDivision division){
        this.divisions.add(division);
    }
    
    public FirstLevelDivision getDivisionById(int id){
        divisions.forEach((div)->{
            if(div.getId() == id){
                division = div;
            }
        });
        
        return division;
    }

    public FirstLevelDivision getDivisionByName(String name){
        divisions.forEach((div)->{
            if(div.getDivision().equals(name)){
                division = div;
            }
        });
        
        return division;
    }
}
