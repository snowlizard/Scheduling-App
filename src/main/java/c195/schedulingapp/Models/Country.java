/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

/**
 * Country Model
 * @author Julian
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
    
    /**
     * get country id
     * @return id 
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * get country name
     * @return name of country 
     */
    public String getCountry(){
        return this.country;
    }
    
    /**
     * get created date time
     * @return create date
     */
    public String getCreateDate(){
        return this.create_date;
    }
    
    /**
     * get created by user
     * @return created by user 
     */
    public String getCreatedBy(){
        return this.created_by;
    }
    
    /**
     * get last update date time
     * @return last update 
     */
    public String getLastUpdate(){
        return this.last_update;
    }
    
    /**
     * get last updated by user
     * @return last updated by 
     */
    public String getLastUpdatedBy(){
        return this.last_updated_by;
    }
    
    /**
     * sets the country id
     * @param id 
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * sets the country name
     * @param country 
     */
    public void setCountry(String country){
        this.country = country;
    }
    
    /**
     * sets the create date time
     * @param createDate 
     */
    public void setCreateDate(String createDate){
        this.create_date = createDate;
    }
    
    /**
     * sets created by
     * @param createdBy 
     */
    public void setCreatedBy(String createdBy){
        this.created_by = createdBy;
    }
    
    /**
     * sets last update
     * @param lastUpdate 
     */
    public void setLastUpdate(String lastUpdate){
        this.last_update = lastUpdate;
    }
    
    /**
     * sets last updated by
     * @param lastUpdatedBy 
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.last_updated_by = lastUpdatedBy;
    }
}
