/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Users Singleton
 * @author Julian
 */
public class Users {
    private static final Users instance = new Users();
    private ObservableList<User> users = FXCollections.observableArrayList();
    
    private Users(){}
    
    /**
     * Users instance
     * @return Users instance
     */
    public static Users getInstance(){
        return instance;
    }
    
    /**
     * List of all users
     * @return ObservableList<User>
     */
    public ObservableList<User> getUsers(){
        return this.users;
    }
    
    /**
     * Empties the list of users
     */
    public void resetUsers(){
        users = FXCollections.observableArrayList();
    }
    
    /**
     * Adds a user to list of users
     * @param newUser User 
     */
    public void addUser(User newUser){
        users.add(newUser);
    }
    
    /**
     * Find user by their id
     * @param id integer
     * @return User
     */
    public User getUser(int id){
        User usr = null;
        for(User u: users){
            if(u.getId() == id)
                usr = u;
        }
        return usr;
    }
    
    /**
     * get user id by their name
     * @param name String
     * @return id
     */
    public int getIdByName(String name){
        int id = -1;
        for(User usr: users){
            if(usr.getUserName().equals(name)){
                id = usr.getId();
            }
        }
        return id;
    }
}
