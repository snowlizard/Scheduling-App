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
 *
 * @author mrjack
 */
public class contactDA extends Connector{
    public contactDA(){}
    
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
    
    public int getID(String name){
        int id = -1;
        String query = "SELECT Contact_ID from contacts"
                + "WHERE Contact_Name = ?;";
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
}
