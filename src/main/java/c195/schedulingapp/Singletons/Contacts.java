/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Contacts Singleton
 * @author Julian
 */
public class Contacts {
    private static final Contacts instance = new Contacts();
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();
    
    private Contacts(){}
    
    /**
     * Returns instance of Contacts
     * @return Contacts
     */
    public static Contacts getInstance(){
        return instance;
    }
    
    /**
     * List of all contacts
     * @return ObservableList<Contact>
     */
    public ObservableList<Contact> getContacts(){
        return contacts;
    }
    
    /**
     * Sets contacts to empty list
     */
    public void resetContacts(){
        this.contacts = FXCollections.observableArrayList();
    }
    
    /**
     * Adds a contact to contacts
     * @param contact Contact
     */
    public void addContact(Contact contact){
        contacts.add(contact);
    }
    
    /**
     * Get a contact by their id
     * @param id integer
     * @return Contact
     */
    public Contact getContact(int id){
        Contact c = null;
        for(Contact contact: contacts){
            if(contact.getId() == id)
                c = contact;
        }
        return c;
    }
    
    /**
     * Gets contact id by their name
     * @param name String
     * @return integer
     */
    public int getIdByName(String name){
        int id = -1;
        for(Contact contact: contacts){
            if(contact.getName().equals(name)){
                id = contact.getId();
            }
        }
        return id;
    }
}
