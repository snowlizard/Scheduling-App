/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mrjack
 */
public class Connector {
    private Connection connector;
    
    public Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connector = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/WGU",
                    "jgon", "Password@1");
        }catch(Exception e){
            System.out.println(e);
        }
        return connector;
    }
}
