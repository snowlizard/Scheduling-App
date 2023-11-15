/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author mrjack
 */
public class Users {
    private static final Users instance = new Users();
    private ObservableList<User> users = FXCollections.observableArrayList();
    
    private Users(){}
    
    public static Users getInstance(){
        return instance;
    }
    
    public ObservableList<User> getUsers(){
        return this.users;
    }
    
    public void resetUsers(){
        users = FXCollections.observableArrayList();
    }
    
    public void addUser(User newUser){
        users.add(newUser);
    }
}
