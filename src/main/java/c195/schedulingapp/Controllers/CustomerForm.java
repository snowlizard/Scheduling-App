/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.FirstLevelDivision;
import c195.schedulingapp.Singletons.CustomersList;
import c195.schedulingapp.Singletons.DivisionsList;

import java.time.LocalDateTime;
import java.time.Instant;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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
    
    CustomersList custInstance = CustomersList.getInstance();
    ObservableList<Customer> customerList = custInstance.getCustomers();
    Customer customer = custInstance.getCurrentCustomer();
    
    DivisionsList divisionList = DivisionsList.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String> choices = FXCollections.observableArrayList();
        ObservableList<FirstLevelDivision> divisions = divisionList.getDivisions();
        
        // lambda function
        divisions.forEach((division) -> {
            choices.add(division.getDivision());
        });
        
        divisionId.setItems(choices);
        
        if(customer != null){
            id.setText(Integer.toString(customer.getId()));
            name.setText(customer.getName());
            address.setText(customer.getAddress());
            postalCode.setText(customer.getPostalCode());
            phone.setText(customer.getPhone());
            divisionId.setValue(divisionList.getDivisionById(
                    customer.getDivisionId()).getDivision());
        }
        id.setDisable(true);
    }
    
    public void onCancel(ActionEvent event){ 
        Node source = (Node) event.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        win.close();
    }
    
    public void onSave(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        String now = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        if(customer != null){
            int index = customerList.indexOf(customer);

            customer.setName(name.getText());
            customer.setAddress(address.getText());
            customer.setPostalCode(postalCode.getText());
            customer.setPhone(phone.getText());
            customer.setLastUpdate(now);
            customer.setLastUpdatedBy(custInstance.getLoggedInUser());
            customer.setDivisionId(index);

            customerList.set(index, customer);
        } else {
            int customerId = customerList.size();
            int divId = divisionList.getDivisionByName(divisionId.getValue()).getId();
            
            Customer newCustomer = new Customer(customerId,
                       name.getText(), 
                    address.getText(), 
                 postalCode.getText(),
                      phone.getText(), 
                 now, 
                  custInstance.getLoggedInUser(),
                 now, 
              custInstance.getLoggedInUser(), 
                 divId);
            
            customerList.add(newCustomer);
        }
        
        win.close();
    }
}
