/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

/**
 * Grabs report of all customers by country and division
 * @author Julian
 */
public class CountryReport {
    private String country;
    private String division;
    private int countryDivTotal;
    
    public CountryReport(String paiz, String div, int ccTotal){
        this.country = paiz;
        this.division = div;
        this.countryDivTotal = ccTotal;
    }
    
    public String getCountry(){
        return this.country;
    }
    
    public String getDivision(){
        return this.division;
    }
    
    public int getCountryDivTotal(){
        return this.countryDivTotal;
    }
    
    public void setCountry(String paiz){
        this.country = paiz;
    }
    
    public void setDivision(String div){
        this.division = div;
    }
    
    public void setCountryDivTotal(int total){
        this.countryDivTotal = total;
    }
}
