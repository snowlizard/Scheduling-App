/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
/**
 *
 * @author mrjack
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
    
    public int getId(){
        return this.id;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public String getLocation(){
        return this.location;
    }
    
    public String getType(){
        return this.type;
    }
    
    public ZonedDateTime getStart(){
        return this.start;
    }
    
    public ZonedDateTime getEnd(){
        return this.end;
    }
    public ZonedDateTime getCreateDate(){
        return this.create_date;
    }
    
    public String getCreatedBy(){
        return this.created_by;
    }
    
    public ZonedDateTime getLastUpdate(){
        return this.last_update;
    }
    
    public String getLastUpdatedBy(){
        return this.last_updated_by;
    }
    
    public int getCustomerId(){
        return this.customer_id;
    }
    
    public int getUserId(){
        return this.user_id;
    }
    
    public int getContactId(){
        return this.contact_id;
    }

    // Setters
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setDescription(String desc){
        this.description = desc;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setStart(ZonedDateTime start){
        this.start = start;
    }
    
    public void setEnd(ZonedDateTime end){
        this.end = end;
    }
    
    public void setCreateDate(ZonedDateTime createDate){
        this.create_date = createDate;
    }
    
    public void setCreatedBy(String createdBy){
        this.created_by = createdBy;
    }
    
    public void setLastUpdate(ZonedDateTime lastUpdate){
        this.last_update = lastUpdate;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.last_updated_by = lastUpdatedBy;
    }
    
    public void setCustomerId(int cId){
        this.customer_id = cId;
    }
    
    public void setUserId(int uId){
        this.user_id = uId;
    }
    
    public void setContactId(int cId){
        this.contact_id = cId;
    }
}
