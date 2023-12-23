/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import c195.schedulingapp.Models.Contact;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Contact database access class
 * @author Julian
 */
public class contactDA extends Connector{
    public contactDA(){}
    
    /**
     * Returns a list of all contacts
     * @return ObservableList<Contact>
     */
    public ObservableList<Contact> getContacts(){
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        String query = "SELECT * FROM contacts";
        
        try{
            ResultSet set = connector.prepareStatement(query).executeQuery();
            while(set.next()){
                contacts.add(new Contact(
                         set.getInt("Contact_ID"),
                        set.getString("Contact_Name"),
                       set.getString("Email")));
            }
        }catch(Exception e){
            System.out.println(e );
        }
        return contacts;
    }
    
    /**
     * Returns the ID of the Contact with
     * the given name
     * @param name String
     * @return int
     */
    public int getID(String name){
        int id = -1;
        String query = "select Contact_ID from contacts where Contact_Name = ?;";
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setString(1, name);
            ResultSet set = pStatement.executeQuery();
            if(set.next()){
                id = set.getInt("Contact_ID");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return id;
    }
    
    /**
     * Get a contact in the database by
     * their id
     * @param id int
     * @return Contact
     */
    public Contact getContact(int id){
        Contact contact = null;
        String query = "select * from contacts where Contact_ID = ?;";
        
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setInt(1, id);
            ResultSet set = pStatement.executeQuery();
            if(set.next()){
                contact = new Contact(
                         set.getInt("Contact_ID"),
                        set.getString("Contact_Name"),
                       set.getString("Email"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return contact;
    }
}
