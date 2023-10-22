/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.FirstLevelDivision;
import c195.schedulingapp.Singletons.CurrentCustomer;
import c195.schedulingapp.Singletons.DivisionsList;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.net.URL;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
/**
 * FXML Controller class
 *
 * @author mrjack
 */
public class CustomerForm implements Initializable{
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private TextField postalCode;
    @FXML private TextField phone;
    @FXML private ChoiceBox<String> divisionId;
    
    CurrentCustomer currentCustomer = CurrentCustomer.getInstance();
    DivisionsList divisionList = DivisionsList.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        Customer customer = currentCustomer.getCustomer();
        ObservableList<String> choices = FXCollections.observableArrayList();
        ObservableList<FirstLevelDivision> divisions = divisionList.getDivisions();
        
        // lambda function
        divisions.forEach((division) -> {
            choices.add(division.getDivision());
        });
        
        divisionId.setItems(choices);
        
        if(customer != null){
            id.setText(Integer.toString(customer.getId()));
            id.setDisable(true);
            name.setText(customer.getName());
            address.setText(customer.getAddress());
            postalCode.setText(customer.getPostalCode());
            phone.setText(customer.getPhone());
            divisionId.setValue(divisionList.getDivisionById(customer.getDivisionId()));
        }
    }
    
    public void onCancel(ActionEvent event){ 
        Node source = (Node) event.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        win.close();
    }
}
