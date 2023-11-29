/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

/**
 * Contact Model
 * @author Julian
 */
public class Contact {
    private int id;
    private String name;
    private String email;
    
    /**
     * Contact constructor
     * @param contactId id
     * @param contactName name
     * @param contactEmail email
     */
    public Contact(int contactId, String contactName, String contactEmail){
        this.id = contactId;
        this.name = contactName;
        this.email = contactEmail;
    }
    
    /**
     * get id
     * @return id 
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * get contact name
     * @return name 
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * get contact email address
     * @return email 
     */
    public String getEmail(){
        return this.email;
    }
    
}
