/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Appointment;
import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.Contact;

import c195.schedulingapp.Singletons.Contacts;
import c195.schedulingapp.Singletons.Customers;
import c195.schedulingapp.Singletons.Users;

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

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
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
    @FXML private ChoiceBox<String> customer;
    @FXML private ChoiceBox<String> contact;
    @FXML private ChoiceBox<String> user;
    @FXML private DatePicker sDate;
    @FXML private DatePicker eDate;
    @FXML private Spinner<?> sHour;
    @FXML private Spinner<?> sMin;
    @FXML private Spinner<?> eHour;
    @FXML private Spinner<?> eMin;
    
    c195.schedulingapp.Singletons.Appointments aptsInstance = c195.schedulingapp.Singletons.Appointments.getInstance();
    Contacts contactInstance = Contacts.getInstance();
    Customers customerInstance = Customers.getInstance();
    Users userInstance = Users.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aptId.setDisable(true);
        Appointment currentApt = aptsInstance.getCurrentAppointment();
        
        // Set Contact choices
        ObservableList<String> contacts = FXCollections.observableArrayList();
        
        contactInstance.getContacts().forEach((contacto) -> {
            contacts.add(contacto.getName());
        });
        contact.setItems(contacts);
        
        // Set Customer choices
        ObservableList<String> customers = FXCollections.observableArrayList();
        
        customerInstance.getCustomers().forEach((cust) -> {
            customers.add(cust.getName());
        });
        customer.setItems(customers);
        
        // Set User choices
        ObservableList<String> users = FXCollections.observableArrayList();
        userInstance.getUsers().forEach((usario) -> {
            users.add(usario.getUserName());
        });
        user.setItems(users);
        
        if(currentApt != null){
            Customer cust = customerInstance.getCustomer(currentApt.getCustomerId());
            Contact cont = contactInstance.getContact(currentApt.getContactId());
            
            aptId.setText(Integer.toString(currentApt.getId()));
            title.setText(currentApt.getTitle());
            desc.setText(currentApt.getDescription());
            location.setText(currentApt.getLocation());
            type.setText(currentApt.getType());
            
            customer.setValue(cust.getName());
            contact.setValue(cont.getName());
            
            sDate.setValue(currentApt.getStart().toLocalDate());
            
            eDate.setValue(currentApt.getEnd().toLocalDate());
        }
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
