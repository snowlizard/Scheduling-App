/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.HelperFunctions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author mrjack
 */
public class CustomersController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void previousScene (ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/home", "Home");
    }
}
