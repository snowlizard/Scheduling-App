/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.Country;
import c195.schedulingapp.Models.FirstLevelDivision;
import c195.schedulingapp.Models.Connector;
import c195.schedulingapp.Singletons.Customers;
import c195.schedulingapp.Singletons.Divisions;
import c195.schedulingapp.Singletons.Countries;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Random;
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
    
    @FXML private TextField id;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private TextField postalCode;
    @FXML private TextField phone;
    @FXML private ChoiceBox<String> divisionId;
    @FXML private ChoiceBox<String> country;
    
    private Customers custInstance = Customers.getInstance();
    private ObservableList<Customer> customerList = custInstance.getCustomers();
    private Customer customer = custInstance.getCurrentCustomer();
    
    private Divisions divisionList = Divisions.getInstance();
    private Countries countryInstance = Countries.getInstance();
    
    private ObservableList<Country> countries = countryInstance.getCountries();
    private ObservableList<FirstLevelDivision> divisions = divisionList.getDivisions();
    
    private ObservableList<String> estados = FXCollections.observableArrayList();
    int customerId = customerList.size();
    
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
            FirstLevelDivision div = divisionList.getDivisionById(division_id);
            Country currCountry = countryInstance.getCountryById(div.getCountryId());
            
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
        String now = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int divId = divisionList.getDivisionByName(divisionId.getValue()).getId();
        
        if(customer != null){
            int index = customerList.indexOf(customer);

            customer.setName(name.getText());
            customer.setAddress(address.getText());
            customer.setPostalCode(postalCode.getText());
            customer.setPhone(phone.getText());
            customer.setLastUpdate(now);
            customer.setLastUpdatedBy(custInstance.getLoggedInUser());
            customer.setDivisionId(divId);

            customerList.set(index, customer);
            connector.updateCustomer(customer);
        } else {
            Random rand = new Random();
            int newId = rand.nextInt(1000);
            while(custInstance.getCustomer(newId) != null){
                newId = rand.nextInt(1000);
            }
            
            Customer newCustomer = new Customer(newId,
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
            connector.insertCustomer(newCustomer);
        }
        
        win.close();
    }
    
    /**
     * Fills the division choice box with the name of each division
     */
    public void setDivisionChoices(){
        String choiceValue = country.getValue();
        ObservableList<String> choices = FXCollections.observableArrayList();
        
        if(choiceValue != null){
            Country selCountry= countryInstance.getCountryByName(choiceValue);
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
}