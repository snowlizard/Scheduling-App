/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * First Level Divisions Singleton
 * @author Julian
 */
public class Divisions {
    private static final Divisions instance = new Divisions();
    
    private ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();
    
    private Divisions(){}
    private FirstLevelDivision division;
    
    /**
     * Gets the Divisions singleton instance
     * @return Divisions
     */
    public static Divisions getInstance(){
        return instance;
    }
    
    /**
     * List of all divisions
     * @return ObersvableList<FirstLevelDivision>
     */
    public ObservableList<FirstLevelDivision> getDivisions(){
        return divisions;
    }
    
    /**
     * Empties the divisions list
     */
    public void resetDivisions(){
        this.divisions = FXCollections.observableArrayList();
    }
    
    /**
     * Adds a division to the list
     * @param division Division
     */
    public void addDivision(FirstLevelDivision division){
        this.divisions.add(division);
    }
    
    /**
     * Gets a division by their id
     * @param id integer
     * @return Division
     */
    public FirstLevelDivision getDivisionById(int id){
        divisions.forEach((div)->{
            if(div.getId() == id){
                division = div;
            }
        });
        
        return division;
    }

    /**
     * Gets a division by their name
     * @param name String
     * @return Division
     */
    public FirstLevelDivision getDivisionByName(String name){
        divisions.forEach((div)->{
            if(div.getDivision().equals(name)){
                division = div;
            }
        });
        
        return division;
    }
}
