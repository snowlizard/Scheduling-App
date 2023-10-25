/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author mrjack
 */
public class Countries {
    private static final Countries instance = new Countries();
    
    private ObservableList<Country> countries = FXCollections.observableArrayList();
    
    private Countries(){}
    private Country country;

    public static Countries getInstance(){
        return instance;
    }
    
    public ObservableList<Country> getCountries(){
        return countries;
    }
    
    public void resetCountries(){
        this.countries = FXCollections.observableArrayList();
    }
    
    public void addCountry(Country paiz){
        this.countries.add(paiz);
    }
    
    public Country getCountryById(int id){
        countries.forEach((div)->{
            if(div.getId() == id){
                country = div;
            }
        });
        
        return country;
    }

    public Country getCountryByName(String name){
        countries.forEach((paiz)->{
            if(paiz.getCountry().equals(name)){
                country = paiz;
            }
        });
        
        return country;
    }
}
