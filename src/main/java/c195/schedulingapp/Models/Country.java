/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

import java.time.LocalDateTime;
/**
 *
 * @author mrjack
 */
public class Country {
    private int id;
    private String country;
    private String create_date;
    private String created_by;
    private String last_update;
    private String last_updated_by;
    
    public Country(int id, String country, String create_date,
            String created_by, String last_update, String last_updated_by){
        this.id             = id;
        this.country         = country;
        this.create_date     = create_date;
        this.created_by      = created_by;
        this.last_update     = last_update;
        this.last_updated_by = last_updated_by;
    }
    
    // Getters
    public int getId(){
        return this.id;
    }
    
    public String getCountry(){
        return this.country;
    }
    
    public String getCreateDate(){
        return this.create_date;
    }
    
    public String getCreatedBy(){
        return this.created_by;
    }
    
    public String getLastUpdate(){
        return this.last_update;
    }
    
    public String getLastUpdatedBy(){
        return this.last_updated_by;
    }
    
    // Setters
    public void setId(int id){
        this.id = id;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
    public void setCreateDate(String createDate){
        this.create_date = createDate;
    }
    
    public void setCreatedBy(String createdBy){
        this.created_by = createdBy;
    }
    
    public void setLastUpdate(String lastUpdate){
        this.last_update = lastUpdate;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.last_updated_by = lastUpdatedBy;
    }
}
