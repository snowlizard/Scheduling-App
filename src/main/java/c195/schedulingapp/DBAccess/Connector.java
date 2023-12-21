/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import c195.schedulingapp.Models.HelperFunctions;
import java.sql.Connection;
import java.sql.DriverManager;

import c195.schedulingapp.Singletons.Customers;

/**
 * Used for SQL connection
 * @author Julian
 */
public class Connector {
    Connection connector;
    String database = "client_schedule";
    Customers customers = Customers.getInstance();
    HelperFunctions helper = new HelperFunctions();
    
    public Connector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connector = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + database,
                    "sqlUser", "Passw0rd!");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}