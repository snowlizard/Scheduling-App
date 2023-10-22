/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;
/**
 *
 * @author mrjack
 */
public class FirstLevelDivision {
    private int id;
    private String division;
    private String create_date;
    private String created_by;
    private String last_update;
    private String last_updated_by;
    private int country_id;
    
    public FirstLevelDivision(int id, String division, String create_date,
        String created_by, String last_update, String last_updated_by,int country_id){
        this.id             = id;
        this.division         = division;
        this.create_date     = create_date;
        this.created_by      = created_by;
        this.last_update     = last_update;
        this.last_updated_by = last_updated_by;
        this.country_id      = country_id;
    }
    
    // Getters
    public int getId(){
        return this.id;
    }
    
    public String getDivision(){
        return this.division;
    }
    
    public int getCountryId(){
        return this.country_id;
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
    
    public void setDivision(String division){
        this.division = division;
    }
    
    public void setCountryId(int countryId){
        this.country_id = countryId;
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
