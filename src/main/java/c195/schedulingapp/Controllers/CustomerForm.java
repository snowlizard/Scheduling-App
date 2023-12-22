/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.Country;
import c195.schedulingapp.Models.FirstLevelDivision;
import c195.schedulingapp.DBAccess.Connector;
import c195.schedulingapp.DBAccess.customerDA;
import c195.schedulingapp.DBAccess.countryDA;
import c195.schedulingapp.DBAccess.divisionDA;
import c195.schedulingapp.DBAccess.userDA;
import c195.schedulingapp.Singletons.Customers;

import java.time.LocalDateTime;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Form for updating and creating customer records
 * @author Julian
 */
public class CustomerForm implements Initializable{
    private Connector connector = new Connector();
    private String errorMsg = "";
    
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private TextField postalCode;
    @FXML private TextField phone;
    @FXML private ChoiceBox<String> divisionId;
    @FXML private ChoiceBox<String> country;
    
    // Database Access
    private countryDA countryDBA = new countryDA();
    private customerDA customerDBA= new customerDA();
    private divisionDA divisionDBA = new divisionDA();
    private userDA userDBA = new userDA();
    
    private Customer customer = customerDBA.getCurrentCustomer();
    
    private ObservableList<Country> countries = countryDBA.getCountries();
    private ObservableList<FirstLevelDivision> divisions = divisionDBA.getDivisions();
    
    private ObservableList<String> estados = FXCollections.observableArrayList();
    
    /**
     * Setup customer form with or without existing customer data
     * Uses lambda function easily add each Country name to the estados list to set
     * the countries choicebox
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){

        // Use lambda function easily add each Country name to the estados list
        countries.forEach((estado) -> estados.add(estado.getCountry()));
        
        country.setItems(estados);
        
        if(customer != null){
            int division_id = customer.getDivisionId();
            FirstLevelDivision div = divisionDBA.getById(division_id);
            Country currCountry = countryDBA.getById(div.getCountryId());
            
            id.setText(Integer.toString(customer.getId()));
            name.setText(customer.getName());
            address.setText(customer.getAddress());
            postalCode.setText(customer.getPostalCode());
            phone.setText(customer.getPhone());
            
            country.setValue(currCountry.getCountry());
            this.setDivisionChoices();
            divisionId.setValue(div.getDivision());
        }
        id.setDisable(true);
    }
    
    /**
     * Cancels the current customer changes and sends back to the customers view
     * @param event 
     */
    public void onCancel(ActionEvent event){ 
        Node source = (Node) event.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        win.close();
    }
    
    /**
     * Processes the current customer form and save/create new customer
     * @param event 
     */
    public void onSave(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        if(this.validateForm()){
            String now = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            int divId = divisionDBA.getByName(divisionId.getValue()).getId();
            if(customer != null){
                customer.setName(name.getText());
                customer.setAddress(address.getText());
                customer.setPostalCode(postalCode.getText());
                customer.setPhone(phone.getText());
                customer.setLastUpdate(now);
                customer.setLastUpdatedBy(userDBA.getLoggedinUser());
                customer.setDivisionId(divId);

                customerDBA.updateCustomer(customer);
            } else {
                Customer newCustomer = new Customer(1,
                           name.getText(), 
                        address.getText(), 
                     postalCode.getText(),
                          phone.getText(), 
                     now, 
                      userDBA.getLoggedinUser(),
                     now, 
                  userDBA.getLoggedinUser(), 
                     divId);
                new customerDA().insertCustomer(newCustomer);
            }
            win.close();
        } else {
            Alert invalidForm = new Alert(AlertType.ERROR,
                errorMsg + " needs to be filled in.", ButtonType.CLOSE);
            invalidForm.showAndWait();
        }
    }
    
    /**
     * Fills the division choice box with the name of each division
     */
    public void setDivisionChoices(){
        String choiceValue = country.getValue();
        ObservableList<String> choices = FXCollections.observableArrayList();
        
        if(choiceValue != null){
            Country selCountry= countryDBA.getByName(choiceValue);
            // Allows simple iteration on divisions list
            // use to add division name to choice box
            divisions.forEach((division) -> {
                if(division.getCountryId() == selCountry.getId()){
                    choices.add(division.getDivision());
                }
            });
            divisionId.setItems(choices);
        }
    }
    
    public Boolean validateForm(){
        errorMsg = "";
        if(name.getText().isEmpty()){
            errorMsg += "Name, ";
        } else if(address.getText().isEmpty()){
            errorMsg += "Address, ";
        } else if(postalCode.getText().isEmpty()){
            errorMsg += "Postal code, ";
        } else if(phone.getText().isEmpty()){
            errorMsg += "Phone, ";
        } else if(divisionId.getValue() == null){
            errorMsg += "Division, ";
        } else if(country.getValue() == null){
            errorMsg += "Country, ";
        } else {
            return true;
        }
        
        return false;
    }
}