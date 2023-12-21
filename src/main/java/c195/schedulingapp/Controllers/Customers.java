/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Appointment;
import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.HelperFunctions;
import c195.schedulingapp.DBAccess.customerDA;
import c195.schedulingapp.DBAccess.appointmentDA;
import c195.schedulingapp.Singletons.Appointments;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.collections.ObservableList;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Customer controller displays all customers
 * @author Julian
 */
public class Customers implements Initializable {
    @FXML private TableView<Customer> customers;
    @FXML private TableColumn<Customer, Integer> id;
    @FXML private TableColumn<Customer, String> name;
    @FXML private TableColumn<Customer, String> address;
    @FXML private TableColumn<Customer, String> postal_code;
    @FXML private TableColumn<Customer, String> phone;
    @FXML private TableColumn<Customer, Integer> division_id;

    appointmentDA aptDBA = new appointmentDA();
    customerDA custDBA = new customerDA();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customers.setItems(custDBA.getCustomers());
        
        id.setCellValueFactory(new PropertyValueFactory<> ("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postal_code.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        division_id.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
    }    
    
    /**
     * Sends the user to the home screen
     * @param event
     * @throws IOException 
     */
    public void previousScene (ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/home", "Home");
    }
    
    /**
     * Sets the current customer to the current selected row if selection is not empty
     * and redirects to the customer form
     * @throws IOException 
     */
    public void editCustomer() throws IOException {
        Customer customer = customers.getSelectionModel().getSelectedItem();
        if(customer != null){
            custDBA.setCurrentCustomer(customer);
            new HelperFunctions().setModal("/fxml/customerForm");
        }else{
            Alert dialog = new Alert(AlertType.ERROR, 
                "Select a column to edit a customer.",
                ButtonType.OK);
            dialog.showAndWait();
        }
    }
    
    /**
     * Sets the current customer to null and opens the customer form
     * @throws IOException 
     */
    public void addCustomer() throws IOException{
        custDBA.setCurrentCustomer(null);
        new HelperFunctions().setModal("/fxml/customerForm");
    }
    
    /**
     * deletes the currently selected model upon confirmation
     */
    public void deleteCustomer() {
        Customer customer = customers.getSelectionModel().getSelectedItem();
        if(customer != null){
            Boolean hasApts = false;
            ObservableList<Appointment> apts = aptDBA.getAppointments();
            for(Appointment apt: apts){
                if(apt.getCustomerId() == customer.getId()){
                    hasApts = true;
                }
            }
            
            if(!hasApts){
                Alert confirm = new Alert(AlertType.CONFIRMATION,
                    "Are you sure you want to delete this customer?",
                       ButtonType.YES, ButtonType.NO);
                confirm.showAndWait();

                if(confirm.getResult() == ButtonType.YES){
                    custDBA.removeCustomer(customer.getId());
                }
            }else{
                Alert aptFound = new Alert(AlertType.ERROR,
                    "Please delete customer appointments before" +
                     " deleting customer.", ButtonType.CLOSE);
                aptFound.showAndWait();
            }
        }else{
            Alert dialog = new Alert(AlertType.ERROR, 
                "Select a column to delete a customer.",
                ButtonType.OK);
            dialog.showAndWait();
        }
    }
}