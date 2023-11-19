/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Appointment;
import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.Contact;
import c195.schedulingapp.Models.User;

import c195.schedulingapp.Singletons.Contacts;
import c195.schedulingapp.Singletons.Customers;
import c195.schedulingapp.Singletons.Users;

import c195.schedulingapp.Models.HelperFunctions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    @FXML private Spinner<Integer> sHour;
    @FXML private Spinner<Integer> sMin;
    @FXML private Spinner<Integer> eHour;
    @FXML private Spinner<Integer> eMin;
    
    c195.schedulingapp.Singletons.Appointments aptsInstance = c195.schedulingapp.Singletons.Appointments.getInstance();
    Contacts contactInstance = Contacts.getInstance();
    Customers customerInstance = Customers.getInstance();
    Users userInstance = Users.getInstance();
    
    HelperFunctions helper = new HelperFunctions();
    
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
        
        // Set Spinners
        sHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        sMin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59));
        eHour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        eMin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59));
        
        if(currentApt != null){
            Customer cust = customerInstance.getCustomer(currentApt.getCustomerId());
            Contact cont = contactInstance.getContact(currentApt.getContactId());
            User usr = userInstance.getUser(currentApt.getUserId());
            
            aptId.setText(Integer.toString(currentApt.getId()));
            title.setText(currentApt.getTitle());
            desc.setText(currentApt.getDescription());
            location.setText(currentApt.getLocation());
            type.setText(currentApt.getType());
            
            // preload choice values
            customer.setValue(cust.getName());
            contact.setValue(cont.getName());
            user.setValue(usr.getUserName());
            
            // preload spinner values
            sHour.getValueFactory().setValue(currentApt.getStart().getHour());
            sMin.getValueFactory().setValue(currentApt.getStart().getMinute());
            eHour.getValueFactory().setValue(currentApt.getEnd().getHour());
            eMin.getValueFactory().setValue(currentApt.getEnd().getMinute());
            
            // preload date values
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
        String validDates = this.validateDates();
        if(!validDates.isEmpty())
            this.invalidDatesAlert(validDates);
        else if(title.getText().isEmpty()){
            this.emptyTitleAlert();
        }

    }
    
    private String validateDates(){
        String errorMsg = "";
        
        if(sDate.getValue() == null || eDate.getValue() == null){
            errorMsg = "Empty date field/s";
        }
        if(!sDate.getValue().equals(eDate.getValue())){
            errorMsg = "Start date and end date not the same";
        }
        
        String sDoW = sDate.getValue().getDayOfWeek().toString();
        
        if(sDoW.equals("SATURDAY") || sDoW.equals("SUNDAY")){
            errorMsg = "Weekend selected";
        }
        
        // Prep Hours
        String newSHour = sHour.getValue() < 10 ? "0" + sHour.getValue().toString() 
                : sHour.getValue().toString();
        String newEHour = eHour.getValue() < 10 ? "0" + eHour.getValue().toString() 
                : eHour.getValue().toString();
        // Prep Minutes
        String newSMin = sMin.getValue() < 10 ? "0" + sMin.getValue().toString() 
                : sMin.getValue().toString();
        String newEMin = eMin.getValue() < 10 ? "0" + eMin.getValue().toString()
                : eMin.getValue().toString();
        String newStart = sDate.getValue().toString() + " " + newSHour +
                ":" + newSMin + ":00";
        String newEnd = eDate.getValue().toString() + " " + newEHour +
                ":" + newEMin + ":00";
        
        if(sHour.getValue() > eHour.getValue()){
            errorMsg = "Start hour after end hour";
        } else if(sHour.getValue().equals(eHour.getValue()) &&
                sMin.getValue() > eMin.getValue()){
            errorMsg = "Start minutes after end minutes";
        } else if(!helper.checkInTime(newStart)){
            errorMsg = "Selected time out of business hours";
        } else if(!helper.checkInTime(newEnd)){
            errorMsg = "Selected time out of business hours";
        }
        return errorMsg;
    }
    
    private void invalidDatesAlert(String errorMsg){
        Alert dialog = new Alert(AlertType.ERROR, 
            "Invalid start/end date time. Please check to make sure the" +
                    " information provided is within our operating hours " +
                    "9AM - 5PM Eastern Standard Time.\n" +
                    errorMsg,
            ButtonType.OK);
        dialog.showAndWait();
    }
    
    private void emptyTitleAlert(){
        Alert dialog = new Alert(AlertType.ERROR, 
            "Title must be filled in to submit appointment.",
            ButtonType.OK);
        dialog.showAndWait();
    }  

}
