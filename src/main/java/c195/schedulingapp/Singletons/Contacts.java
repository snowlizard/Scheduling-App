/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author mrjack
 */
public class Contacts {
    private static final Contacts instance = new Contacts();
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();
    
    private Contacts(){}
    
    public static Contacts getInstance(){
        return instance;
    }
    
    public ObservableList<Contact> getContacts(){
        return contacts;
    }
    
    public void resetContacts(){
        this.contacts = FXCollections.observableArrayList();
    }
    
    public void addContact(Contact contact){
        contacts.add(contact);
    }
}