/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

import java.time.ZonedDateTime;
/**
 * Appointment Model
 * @author Julian
 */
public class Appointment {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private ZonedDateTime create_date;
    private String created_by;
    private ZonedDateTime last_update;
    private String last_updated_by;
    private int customer_id;
    private int user_id;
    private int contact_id;
    
    /**
     * Initialize Appointment object
     * @param id integer
     * @param title String
     * @param description String
     * @param location String
     * @param type String
     * @param start ZonedDateTime
     * @param end ZonedDateTime
     * @param create_date ZonedDateTime
     * @param created_by String
     * @param last_update ZonedDateTime
     * @param last_updated_by String
     * @param cust_id integer
     * @param u_id integer
     * @param cont_id integer
     */
    public Appointment(int id, String title, String description, String location,
            String type, ZonedDateTime start, ZonedDateTime end,
            ZonedDateTime create_date, String created_by, ZonedDateTime last_update,
            String last_updated_by, int cust_id, int u_id, int cont_id){
        this.id             = id;
        this.title          = title;
        this.description     = description;
        this.location        = location;
        this.type            = type;
        this.start           = start;
        this.end             = end;
        this.create_date     = create_date;
        this.created_by      = created_by;
        this.last_update     = last_update;
        this.last_updated_by = last_updated_by;
        this.customer_id     = cust_id;
        this.user_id         = u_id;
        this.contact_id      = cont_id;
    }
    
    /**
     * Appointment ID
     * @return Appointment ID
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * get title
     * @return appointment title
     */
    public String getTitle(){
        return this.title;
    }
    
    /**
     * get description
     * @return appointment description 
     */
    public String getDescription(){
        return this.description;
    }
    
    /**
     * get location
     * @return appointment location 
     */
    public String getLocation(){
        return this.location;
    }
    
    /**
     * get appointment type
     * @return appointment type 
     */
    public String getType(){
        return this.type;
    }
    
    /**
     * get start date time
     * @return appointment start date time 
     */
    public ZonedDateTime getStart(){
        return this.start;
    }
    
    /**
     * get end date time
     * @return appointment end date time 
     */
    public ZonedDateTime getEnd(){
        return this.end;
    }
    
    /**
     * get create date
     * @return date appointment created date 
     */
    public ZonedDateTime getCreateDate(){
        return this.create_date;
    }
    
    /**
     * get create by
     * @return created by user name 
     */
    public String getCreatedBy(){
        return this.created_by;
    }
    
    /**
     * get last update
     * @return date time of last update 
     */
    public ZonedDateTime getLastUpdate(){
        return this.last_update;
    }
    
    /**
     * get last update by
     * @return name of last person who updated appointment
     */
    public String getLastUpdatedBy(){
        return this.last_updated_by;
    }
    
    /**
     * get customer id
     * @return customer id 
     */
    public int getCustomerId(){
        return this.customer_id;
    }
    
    /**
     * get user id
     * @return user id 
     */
    public int getUserId(){
        return this.user_id;
    }
    
    /**
     * get contact id
     * @return contact id 
     */
    public int getContactId(){
        return this.contact_id;
    }
    
    /**
     * sets the appointment id
     * @param id unique appointment id
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * sets the title
     * @param title String
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    /**
     * sets the description
     * @param desc String
     */
    public void setDescription(String desc){
        this.description = desc;
    }
    
    /**
     * sets the location
     * @param location String
     */
    public void setLocation(String location){
        this.location = location;
    }
    
    /**
     * sets the type
     * @param type String
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     * sets the start
     * @param start ZonedDateTime
     */
    public void setStart(ZonedDateTime start){
        this.start = start;
    }
    
    /**
     * sets the end
     * @param end ZonedDateTime
     */
    public void setEnd(ZonedDateTime end){
        this.end = end;
    }
    
    /**
     * sets the create date
     * @param createDate ZonedDateTime
     */
    public void setCreateDate(ZonedDateTime createDate){
        this.create_date = createDate;
    }
    
    /**
     * sets created by
     * @param createdBy String
     */
    public void setCreatedBy(String createdBy){
        this.created_by = createdBy;
    }
    
    /**
     * sets the last update
     * @param lastUpdate ZonedDateTime
     */
    public void setLastUpdate(ZonedDateTime lastUpdate){
        this.last_update = lastUpdate;
    }
    
    /**
     * sets last updated by
     * @param lastUpdatedBy String
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.last_updated_by = lastUpdatedBy;
    }
    
    /**
     * sets the customer id
     * @param cId integer
     */
    public void setCustomerId(int cId){
        this.customer_id = cId;
    }
    
    /**
     * sets the user id
     * @param uId integer
     */
    public void setUserId(int uId){
        this.user_id = uId;
    }
    
    /**
     * sets the contact id
     * @param cId integer
     */
    public void setContactId(int cId){
        this.contact_id = cId;
    }
}
