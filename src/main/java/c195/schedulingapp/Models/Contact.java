/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

/**
 *
 * @author mrjack
 */
public class Contact {
    private int id;
    private String name;
    private String email;
    
    public Contact(int contactId, String contactName, String contactEmail){
        this.id = contactId;
        this.name = contactName;
        this.email = contactEmail;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getEmail(){
        return this.email;
    }
    
}
