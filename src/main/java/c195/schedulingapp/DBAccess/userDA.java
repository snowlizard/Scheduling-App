/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author mrjack
 */
public class userDA extends Connector{

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
                    customers.setLoggedInUser(userName);
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return found;
    }
}
