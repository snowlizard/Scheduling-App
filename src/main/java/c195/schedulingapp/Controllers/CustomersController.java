/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Connector;
import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.HelperFunctions;
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
public class CustomersController implements Initializable {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connector connector = new Connector();
        customers.setItems(connector.getCustomers());
        
        id.setCellValueFactory(new PropertyValueFactory<Customer, Integer> ("id"));
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postal_code.setCellValueFactory(new PropertyValueFactory<Customer, String>("postal_code"));
        phone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        create_date.setCellValueFactory(new PropertyValueFactory<Customer, String>("create_date"));
        created_by.setCellValueFactory(new PropertyValueFactory<Customer, String>("created_by"));
        last_update.setCellValueFactory(new PropertyValueFactory<Customer, String>("last_update"));
        last_updated_by.setCellValueFactory(new PropertyValueFactory<Customer, String>("last_updated_by"));
        division_id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("division_id"));
        
    }    
    
    public void previousScene (ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/home", "Home");
    }
}
