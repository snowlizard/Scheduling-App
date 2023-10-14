/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.HelperFunctions;
import c195.schedulingapp.Models.HelperFunctions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author mrjack
 */
public class HomeController implements Initializable {
    private Scene scene;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("Hi home.");
    }
    
    public void logout(ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/login", "Scheduling App");
    }
    
    public void customers(ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/customers", "Customers");
    }
}
