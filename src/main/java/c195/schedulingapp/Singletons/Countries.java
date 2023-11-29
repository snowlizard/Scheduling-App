/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Countries Singleton
 * @author Julian
 */
public class Countries {
    private static final Countries instance = new Countries();
    
    private ObservableList<Country> countries = FXCollections.observableArrayList();
    
    private Countries(){}
    private Country country;

    /**
     * Gets instance
     * @return Countries instance
     */
    public static Countries getInstance(){
        return instance;
    }
    
    /**
     * List of all countries
     * @return ObservableList<Country>
     */
    public ObservableList<Country> getCountries(){
        return countries;
    }
    
    /**
     * sets countries to an empty list
     */
    public void resetCountries(){
        this.countries = FXCollections.observableArrayList();
    }
    
    /**
     * Adds a country to the list of countries
     * @param paiz Country
     */
    public void addCountry(Country paiz){
        this.countries.add(paiz);
    }
    
    /**
     * Gets a country by their id
     * @param id integer
     * @return Country
     */
    public Country getCountryById(int id){
        countries.forEach((div)->{
            if(div.getId() == id){
                country = div;
            }
        });
        
        return country;
    }
    
    /**
     * Gets a country by name
     * @param name String
     * @return Country
     */
    public Country getCountryByName(String name){
        countries.forEach((paiz)->{
            if(paiz.getCountry().equals(name)){
                country = paiz;
            }
        });
        
        return country;
    }
}
