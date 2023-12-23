/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import c195.schedulingapp.Models.User;
import c195.schedulingapp.DBAccess.userDA;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author mrjack
 */
public class userDA extends Connector{
    private static String loggedinUser;
    
    public userDA(){}
    
    public String getLoggedinUser(){
        return loggedinUser;
    }
    
    public void setLoggedinUser(String username){
        loggedinUser = username;
    }

    /**
     * Checks for valid login
     * @param userName String
     * @param password String
     * @return true if valid
     */
    public Boolean validLogin(String userName, String password){
        Boolean found = false;
        String query = "SELECT * FROM users WHERE User_Name = ? AND Password = ?;";
        
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setString(1, userName);
            pStatement.setString(2, password);
            ResultSet set = pStatement.executeQuery();
            while(set.next()){
                if(userName.equals(set.getString("User_Name")) 
                        && password.equals(set.getString("Password"))){
                    found = true;
                    new userDA().setLoggedinUser(userName);
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return found;
    }
    
    public ObservableList<User> getUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();
        String query = "select * from users;";
        
        try{
            ResultSet set = connector.createStatement().executeQuery(query);
            while(set.next()){
                users.add(new User(
                        set.getInt("User_ID"),
                        set.getString("User_Name")));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return users;
    }
    
    public int getIdByName(String name){
        int id = -1;
        String query = "select User_ID from users where User_Name = ?;";
        
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setString(1, name);
            ResultSet set = pStatement.executeQuery();
            if(set.next()){
                id = set.getInt("User_ID");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return id;
    }
    
    public User getUser(int id){
        User user = null;
        String query = "select * from users where User_ID = ?;";
        
        try{
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setInt(1, id);
            ResultSet set = pStatement.executeQuery();
            if(set.next()){
                user = new User(set.getInt("User_ID"),
                                set.getString("User_Name"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return user;
    }
}
