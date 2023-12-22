/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

import c195.schedulingapp.DBAccess.countryDA;
import c195.schedulingapp.DBAccess.divisionDA;

/**
 * Customer Model
 * @author Julian
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String postal_code;
    private String phone;
    private String create_date;
    private String created_by;
    private String last_update;
    private String last_updated_by;
    private int division_id;
    private String division_name;
    private String country;
    
    public Customer(int id, String name, String address, String postal_code,
            String phone, String create_date, String created_by,
            String last_update, String last_updated_by, int division_id){
        this.id              = id;
        this.name            = name;
        this.address         = address;
        this.postal_code     = postal_code;
        this.phone           = phone;
        this.create_date     = create_date;
        this.created_by      = created_by;
        this.last_update     = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id     = division_id;
    }
    /**
     * Customer id
     * @return id 
     */
    public int getId(){
        return id;
    }
    
    /**
     * Customer name
     * @return name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Customer address
     * @return address
     */
    public String getAddress(){
        return this.address;
    }
    
    /**
     * Customer postal/zip code
     * @return postal code
     */
    public String getPostalCode(){
        return this.postal_code;
    }
    
    /**
     * Customer phone number
     * @return phone
     */
    public String getPhone(){
        return this.phone;
    }
    
    /**
     * Customer create date
     * @return create date
     */
    public String getCreateDate(){
        return this.create_date;
    }
    
    /**
     * Customer created by
     * @return created by
     */
    public String getCreatedBy(){
        return this.created_by;
    }
    
    /**
     * Last update
     * @return last update
     */
    public String getLastUpdate(){
        return this.last_update;
    }
    
    /**
     * Customer last updated by
     * @return last updated by
     */
    public String getLastUpdatedBy(){
        return this.last_updated_by;
    }
    
    /**
     * Division id
     * @return integer
     */
    public int getDivisionId(){
        return this.division_id;
    }
    
    public String getDivisionName(){
        return new divisionDA().getById(division_id).getDivision();
    }
    
    public String getCountry(){
        return new countryDA().getById(new divisionDA().getById(division_id).getCountryId()).getCountry();
    }
    
    /**
     * sets the customer id
     * @param id integer
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * sets the customer name
     * @param name string
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * sets the customer address
     * @param address string
     */
    public void setAddress(String address){
        this.address = address;
    }
    
    /**
     * sets the customer postal code
     * @param postal_code string
     */
    public void setPostalCode(String postal_code){
        this.postal_code = postal_code;
    }
    
    /**
     * sets the customer phone number
     * @param phone string
     */
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    /**
     * sets the create date time
     * @param createDate string
     */
    public void setCreateDate(String createDate){
        this.create_date = createDate;
    }
    
    /**
     * sets the created by
     * @param createdBy string 
     */
    public void setCreatedBy(String createdBy){
        this.created_by = createdBy;
    }
    
    /**
     * sets the last update
     * @param lastUpdate string 
     */
    public void setLastUpdate(String lastUpdate){
        this.last_update = lastUpdate;
    }
    
    /**
     * sets customer last updated by
     * @param lastUpdatedBy string
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.last_updated_by = lastUpdatedBy;
    }
    
    /**
     * sets the division id
     * @param division_id integer
     */
    public void setDivisionId(int division_id){
        this.division_id = division_id;
    }
}
