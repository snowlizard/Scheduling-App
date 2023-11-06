/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author mrjack
 */
public class AppointmentForm implements Initializable {

    @FXML private TextField aptId;
    @FXML private TextField title;
    @FXML private TextField desc;
    @FXML private TextField location;
    @FXML private TextField type;
    @FXML private ChoiceBox<?> customer;
    @FXML private ChoiceBox<?> contact;
    @FXML private ChoiceBox<?> user;
    @FXML private DatePicker sDate;
    @FXML private DatePicker eDate;
    @FXML private Spinner<?> sHour;
    @FXML private Spinner<?> sMin;
    @FXML private Spinner<?> eHour;
    @FXML private Spinner<?> eMin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void onCancel(ActionEvent event){ 
        Node source = (Node) event.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        win.close();
    }

    @FXML
    private void onSave(ActionEvent event) {
    }

}
