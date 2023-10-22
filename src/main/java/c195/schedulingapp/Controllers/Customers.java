/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.HelperFunctions;
import c195.schedulingapp.Singletons.CurrentCustomer;
import c195.schedulingapp.Singletons.CustomersList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author mrjack
 */
public class Customers implements Initializable {
    @FXML private TableView<Customer> customers;
    @FXML private TableColumn<Customer, Integer> id;
    @FXML private TableColumn<Customer, String> name;
    @FXML private TableColumn<Customer, String> address;
    @FXML private TableColumn<Customer, String> postal_code;
    @FXML private TableColumn<Customer, String> phone;
    @FXML private TableColumn<Customer, String> create_date;
    @FXML private TableColumn<Customer, String> created_by;
    @FXML private TableColumn<Customer, String> last_update;
    @FXML private TableColumn<Customer, String> last_updated_by;
    @FXML private TableColumn<Customer, Integer> division_id;

    CurrentCustomer currentCustomer = CurrentCustomer.getInstance();
    ObservableList<Customer> customersList = CustomersList.getInstance().getCustomers();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customers.setItems(customersList);
        
        id.setCellValueFactory(new PropertyValueFactory<> ("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postal_code.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        create_date.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        created_by.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        last_update.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        last_updated_by.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        division_id.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
    }    
    
    public void previousScene (ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/home", "Home");
    }
    
    public void editCustomer() throws IOException {
        Customer customer = customers.getSelectionModel().getSelectedItem();
        if(customer != null){
            currentCustomer.setCustomer(customer);
            new HelperFunctions().setModal("/fxml/customerForm");
        }
    }
    
    public void addCustomer() throws IOException{
        currentCustomer.setCustomer(null);
        new HelperFunctions().setModal("/fxml/customerForm");
    }
}
