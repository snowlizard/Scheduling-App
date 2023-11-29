/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;
/**
 * First Level Division Model
 * @author Julian
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
    
    /**
     * Division id
     * @return integer
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * gets division name
     * @return string
     */
    public String getDivision(){
        return this.division;
    }
    
    /**
     * gets the country id
     * @return integer
     */
    public int getCountryId(){
        return this.country_id;
    }
    
    /**
     * gets the created date
     * @return string
     */
    public String getCreateDate(){
        return this.create_date;
    }
    
    /**
     * gets created by
     * @return string
     */
    public String getCreatedBy(){
        return this.created_by;
    }
    
    /**
     * gets last update
     * @return string
     */
    public String getLastUpdate(){
        return this.last_update;
    }
    
    /**
     * gets last updated by
     * @return string
     */
    public String getLastUpdatedBy(){
        return this.last_updated_by;
    }
    
    /**
     * sets id
     * @param id integer 
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * sets division names
     * @param division string 
     */
    public void setDivision(String division){
        this.division = division;
    }
    
    /**
     * sets country id
     * @param countryId integer
     */
    public void setCountryId(int countryId){
        this.country_id = countryId;
    }
    
    /**
     * sets create date
     * @param createDate string 
     */
    public void setCreateDate(String createDate){
        this.create_date = createDate;
    }
    
    /**
     * sets created by
     * @param createdBy string 
     */
    public void setCreatedBy(String createdBy){
        this.created_by = createdBy;
    }
    
    /**
     * sets last update
     * @param lastUpdate string 
     */
    public void setLastUpdate(String lastUpdate){
        this.last_update = lastUpdate;
    }
    
    /**
     * sets last updated by
     * @param lastUpdatedBy string 
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.last_updated_by = lastUpdatedBy;
    }
}
