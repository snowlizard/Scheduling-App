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
    // Getters
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public String getPostalCode(){
        return this.postal_code;
    }
    
    public String getPhone(){
        return this.phone;
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
    
    public int getDivision(){
        return this.division_id;
    }
    
    // Setters
    public void setId(int id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public void setPostalCode(String postal_code){
        this.postal_code = postal_code;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
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
    
    public void setDivision(int division_id){
        this.division_id = division_id;
    }
}
