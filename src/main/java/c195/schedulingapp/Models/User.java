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
    
    // Getters
    public int getId(){
        return this.id;
    }
    
    public String getUserName(){
        return this.user_name;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public LocalDateTime getCreateDate(){
        return this.create_date;
    }
    
    public String getCreatedBy(){
        return this.created_by;
    }
    
    public LocalDateTime getLastUpdate(){
        return this.last_update;
    }
    
    public String getLastUpdatedBy(){
        return this.last_updated_by;
    }
    
    // Setters
    public void setId(int id){
        this.id = id;
    }
    
    public void setUserName(String user_name){
        this.user_name = user_name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setCreateDate(LocalDateTime createDate){
        this.create_date = createDate;
    }
    
    public void setCreatedBy(String createdBy){
        this.created_by = createdBy;
    }
    
    public void setLastUpdate(LocalDateTime lastUpdate){
        this.last_update = lastUpdate;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.last_updated_by = lastUpdatedBy;
    }
}
