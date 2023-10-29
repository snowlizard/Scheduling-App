/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.Country;
import c195.schedulingapp.Models.FirstLevelDivision;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        countries.forEach((estado) -> {
            estados.add(estado.getCountry());
        });
        
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
        }
        
        win.close();
    }
    
    public void setDivisionChoices(){
        String choiceValue = country.getValue();
        ObservableList<String> choices = FXCollections.observableArrayList();
        
        if(choiceValue != null){
            Country selCountry= countryInstance.getCountryByName(choiceValue);
            // lambda function
            divisions.forEach((division) -> {
                if(division.getCountryId() == selCountry.getId()){
                    choices.add(division.getDivision());
                }
            });
            divisionId.setItems(choices);
        }
    }
}
