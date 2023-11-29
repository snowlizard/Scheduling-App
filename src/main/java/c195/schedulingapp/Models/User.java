/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

import java.time.LocalDateTime;
/**
 * User Model
 * @author Julian
 */
public class User {
    private int id;
    private String user_name;
    private String password;
    private LocalDateTime create_date;
    private String created_by;
    private LocalDateTime last_update;
    private String last_updated_by;
    
    public User(int id, String user_name){
        this.id              = id;
        this.user_name       = user_name;
    }
    
    /**
     * gets user id
     * @return integer
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * gets user name
     * @return string
     */
    public String getUserName(){
        return this.user_name;
    }
    
    /**
     * gets user password
     * @return string
     */
    public String getPassword(){
        return this.password;
    }
    
    /**
     * gets create date
     * @return LocalDateTime
     */
    public LocalDateTime getCreateDate(){
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
     * @return LocalDateTime
     */
    public LocalDateTime getLastUpdate(){
        return this.last_update;
    }
    
    /**
     * gets last updated by
     * @return String
     */
    public String getLastUpdatedBy(){
        return this.last_updated_by;
    }
    
    /**
     * sets the user id
     * @param id integer
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * sets the username
     * @param user_name string 
     */
    public void setUserName(String user_name){
        this.user_name = user_name;
    }
    
    /**
     * sets user password
     * @param password string 
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     * sets create date
     * @param createDate LocalDateTime 
     */
    public void setCreateDate(LocalDateTime createDate){
        this.create_date = createDate;
    }
    
    /**
     * sets the created by
     * @param createdBy String
     */
    public void setCreatedBy(String createdBy){
        this.created_by = createdBy;
    }
    
    /**
     * sets the last update
     * @param lastUpdate LocalDateTime
     */
    public void setLastUpdate(LocalDateTime lastUpdate){
        this.last_update = lastUpdate;
    }
    
    /**
     * sets last updated by
     * @param lastUpdatedBy String 
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.last_updated_by = lastUpdatedBy;
    }
}
