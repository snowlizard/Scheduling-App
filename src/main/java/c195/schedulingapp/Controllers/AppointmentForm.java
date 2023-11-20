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
import java.time.ZonedDateTime;
import java.util.Random;
import java.time.ZoneId;
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
    Appointment currentApt = aptsInstance.getCurrentAppointment();
    
    HelperFunctions helper = new HelperFunctions();
    private ZoneId localZone = ZoneId.systemDefault();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aptId.setDisable(true);
        
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
        
        // Appointment form was loaded with pre-existing appointment
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
        Node source = (Node) event.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        Boolean validForm = this.validateForm();
        if(validForm){
            ZonedDateTime newStartTime = helper.getZDT(this.getStartStr());
            ZonedDateTime newEndTime = helper.getZDT(this.getEndStr());
            ZonedDateTime createdDate = ZonedDateTime.now(localZone);
            String createdBy = customerInstance.getLoggedInUser();
            String updatedBy = customerInstance.getLoggedInUser();
            ZonedDateTime lastUpdate = ZonedDateTime.now(localZone);
            int customerId = customerInstance.getIdByName(customer.getValue());
            int contactId = contactInstance.getIdByName(contact.getValue());
            int userId = userInstance.getIdByName(user.getValue());
            
            if(currentApt != null){
                createdDate = currentApt.getCreateDate();
                createdBy = currentApt.getCreatedBy();
                
            }else{
                Random rand = new Random();
                int newId = rand.nextInt(1000);
                while(aptsInstance.getAptById(newId) != null){
                    newId = rand.nextInt(1000);
                }
                
                Appointment newApt = new Appointment(newId, title.getText(),
                    desc.getText(), location.getText(), type.getText(),
                    newStartTime, newEndTime, createdDate, createdBy,
                    lastUpdate, updatedBy, customerId, userId,
                    contactId);
                aptsInstance.addAppointment(newApt);
            }
        }
        
        win.close();
    }
    
    private Boolean validateForm(){
        Boolean valid = true;
        String validDates = this.validateDates();
        if(!validDates.isEmpty()){
            this.invalidDatesAlert(validDates);
            valid = false;
        }
        else if(title.getText().isEmpty()){
            this.emptyValueAlert("Title must be filled in to submit appointment.");
            valid = false;
        } else if(customer.getValue() == null){
            this.emptyValueAlert("Customer must be filled in to submit appointment.");
            valid = false;
        } else if(contact.getValue() == null){
            this.emptyValueAlert("Contact must be filled in to submit appointment.");
            valid = false;
        } else if(user.getValue() == null){
            this.emptyValueAlert("User must be filled in to submit appointment.");
            valid = false;
        }
        return valid;
    }
    
    private String validateDates(){
        
        if(sDate.getValue() == null || eDate.getValue() == null){
            return "Empty date field/s";
        }
        if(!sDate.getValue().equals(eDate.getValue())){
            return "Start date and end date not the same";
        }
        
        String sDoW = sDate.getValue().getDayOfWeek().toString();
        
        if(sDoW.equals("SATURDAY") || sDoW.equals("SUNDAY")){
            return "Weekend selected";
        }
        
        String newStart = this.getStartStr();
        String newEnd = this.getEndStr();
        
        if(sHour.getValue() > eHour.getValue()){
            return "Start hour after end hour";
        } else if(sHour.getValue().equals(eHour.getValue()) &&
                sMin.getValue() > eMin.getValue()){
            return "Start minutes after end minutes";
        } else if(!helper.checkInTime(newStart)){
            return "Selected time out of business hours";
        } else if(!helper.checkInTime(newEnd)){
            return "Selected time out of business hours";
        }
        return "";
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
    
    private void emptyValueAlert(String msg){
        Alert dialog = new Alert(AlertType.ERROR, 
            msg,
            ButtonType.OK);
        dialog.showAndWait();
    }
    
    private String getStartStr(){
        String newSHour = sHour.getValue() < 10 ? "0" + sHour.getValue().toString() 
                : sHour.getValue().toString();
        String newSMin = sMin.getValue() < 10 ? "0" + sMin.getValue().toString() 
                : sMin.getValue().toString();

        String newStart = sDate.getValue().toString() + " " + newSHour +
                ":" + newSMin + ":00";
        return newStart;
    }
    
    private String getEndStr(){
        String newEHour = eHour.getValue() < 10 ? "0" + eHour.getValue().toString() 
                : eHour.getValue().toString();
        String newEMin = eMin.getValue() < 10 ? "0" + eMin.getValue().toString()
                : eMin.getValue().toString();
        String newEnd = eDate.getValue().toString() + " " + newEHour +
                ":" + newEMin + ":00";
        return newEnd;
    }

}
