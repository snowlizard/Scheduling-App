/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Appointment;
import c195.schedulingapp.Models.Customer;
import c195.schedulingapp.Models.Contact;
import c195.schedulingapp.Models.User;

import c195.schedulingapp.DBAccess.appointmentDA;
import c195.schedulingapp.DBAccess.customerDA;
import c195.schedulingapp.DBAccess.contactDA;
import c195.schedulingapp.DBAccess.userDA;

import c195.schedulingapp.Models.HelperFunctions;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.ZonedDateTime;
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
 * Appointment form controller used for updating or creating appointments
 * @author Julian
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
    
    appointmentDA appointmentDBA = new appointmentDA();
    customerDA customerDBA = new customerDA();
    contactDA contactDBA = new contactDA();
    userDA userDBA = new userDA();
    
    Appointment currentApt = appointmentDBA.getCurrent();
    
    HelperFunctions helper = new HelperFunctions();
    private ZoneId localZone = ZoneId.systemDefault();
    
    /**
     * Initialize Appointment form
     * Uses lambda function to iterate easily
     * between each contact in the contacts list
     * and populate a new list that contains each
     * contacts name
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aptId.setDisable(true);
        ObservableList<String> contacts = FXCollections.observableArrayList();
        
        // Lambda function used to get contact names
        contactDBA.getContacts().forEach((contacto) -> {
            contacts.add(contacto.getName());
        });
        contact.setItems(contacts);
        
        // Set Customer choices
        ObservableList<String> customers = FXCollections.observableArrayList();
        
        customerDBA.getCustomers().forEach((cust) -> {
            customers.add(cust.getName());
        });
        customer.setItems(customers);
        
        // Set User choices
        ObservableList<String> users = FXCollections.observableArrayList();
        userDBA.getUsers().forEach((usario) -> {
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
            Customer cust = customerDBA.getCustomer(currentApt.getCustomerId());
            Contact cont = contactDBA.getContact(currentApt.getContactId());
            User usr = userDBA.getUser(currentApt.getUserId());
            
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
    
    /**
     * Closes the current screen without saving and changes made to the form
     * @param event ActionEvent
     */
    @FXML
    private void onCancel(ActionEvent event){ 
        Node source = (Node) event.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        win.close();
    }

    /**
     * Saves the current fields into an existing/new appointment
     * @param event ActionEvent
     */
    @FXML
    private void onSave(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        Boolean validForm = this.validateForm();
        
        if(validForm){
            ZonedDateTime newStartTime = helper.getZDT(this.getStartStr());
            ZonedDateTime newEndTime = helper.getZDT(this.getEndStr());
            
            
            
            ZonedDateTime createdDate = ZonedDateTime.now(localZone);
            String createdBy = userDBA.getLoggedinUser();
            String updatedBy = userDBA.getLoggedinUser();
            ZonedDateTime lastUpdate = ZonedDateTime.now(localZone);
            int customerId = customerDBA.getIdByName(customer.getValue());
            int contactId = contactDBA.getID(contact.getValue());
            int userId = userDBA.getIdByName(user.getValue());
            
            if(currentApt != null){
                if(this.checkForConflicts(newEndTime, newEndTime, currentApt.getId())){
                    currentApt.setTitle(title.getText());
                    currentApt.setDescription(desc.getText());
                    currentApt.setLocation(location.getText());
                    currentApt.setType(type.getText());
                    currentApt.setStart(newStartTime);
                    currentApt.setEnd(newEndTime);
                    currentApt.setLastUpdate(lastUpdate);
                    currentApt.setLastUpdatedBy(updatedBy);
                    currentApt.setCustomerId(customerId);
                    currentApt.setUserId(userId);
                    currentApt.setContactId(contactId);

                    appointmentDBA.updateAppointment(currentApt);
                    win.close();
                }
            }else{
                if(this.checkForConflicts(newEndTime, newEndTime, -1)){
                    Appointment newApt = new Appointment(1, title.getText(),
                        desc.getText(), location.getText(), type.getText(),
                        newStartTime, newEndTime, createdDate, createdBy,
                        lastUpdate, updatedBy, customerId, userId,
                        contactId);

                    appointmentDBA.insertAppointment(newApt);
                    win.close();
                }
            }
        }
    }
    
    /**
     * Validates the form on submission
     * @return true if valid false if invalid
     */
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
        } else if (location.getText().isEmpty()){
            this.emptyValueAlert("Location must be filled in to submit appointment.");
            valid = false;
        } else if (type.getText().isEmpty()){
            this.emptyValueAlert("Type must be filled in to submit appointment.");
            valid = false;
        } else if(desc.getText().isEmpty()){
            this.emptyValueAlert("Description must be filled in to submit appointment.");
            valid = false;
        }
        return valid;
    }
    
    /**
     * Checks the start and end dates on the form to ensure
     * they are valid dates
     * @return String
     */
    private String validateDates(){
        if(sDate.getValue() == null || eDate.getValue() == null){
            return "Empty date field/s";
        }
        if(!sDate.getValue().equals(eDate.getValue())){
            return "Start date and end date not the same";
        }
        
        String newStart = this.getStartStr();
        String newEnd = this.getEndStr();
        
        if(sHour.getValue() > eHour.getValue()){
            return "Start hour after end hour";
        } else if(sHour.getValue().equals(eHour.getValue()) &&
                sMin.getValue() > eMin.getValue()){
            return "Start minutes after end minutes";
        } else if(sHour.getValue().equals(eHour.getValue()) &&
                sMin.getValue().equals(eMin.getValue())){
            return "Minutes cannot be the same.";
        } else if(!helper.checkInTime(newStart)){
            return "Selected time out of business hours";
        } else if(!helper.checkInTime(newEnd)){
            return "Selected time out of business hours";
        }
        return "";
    }
    
    /**
     * Alerts the user of invalid dates
     * @param errorMsg string based on specific error
     */
    private void invalidDatesAlert(String errorMsg){
        Alert dialog = new Alert(AlertType.ERROR, 
            "Invalid start/end date time. Please check to make sure the" +
                    " information provided is within our operating hours " +
                    "8AM - 10PM Eastern Standard Time.\n" +
                    errorMsg,
            ButtonType.OK);
        dialog.showAndWait();
    }
    
    /**
     * Alerts the user of empty value
     * @param msg error message
     */
    private void emptyValueAlert(String msg){
        Alert dialog = new Alert(AlertType.ERROR, 
            msg,
            ButtonType.OK);
        dialog.showAndWait();
    }
    
    /**
     * Composes the start date based on the date picker and spinner hour and minutes
     * @return format ready date time string
     */
    private String getStartStr(){
        String newSHour = sHour.getValue() < 10 ? "0" + sHour.getValue().toString() 
                : sHour.getValue().toString();
        String newSMin = sMin.getValue() < 10 ? "0" + sMin.getValue().toString() 
                : sMin.getValue().toString();

        String newStart = sDate.getValue().toString() + " " + newSHour +
                ":" + newSMin + ":00";
        return newStart;
    }
    
    /**
     * Composes the end date based on the date picker and spinner hour and minutes
     * @return format ready date time string
     */
    private String getEndStr(){
        String newEHour = eHour.getValue() < 10 ? "0" + eHour.getValue().toString() 
                : eHour.getValue().toString();
        String newEMin = eMin.getValue() < 10 ? "0" + eMin.getValue().toString()
                : eMin.getValue().toString();
        String newEnd = eDate.getValue().toString() + " " + newEHour +
                ":" + newEMin + ":00";
        return newEnd;
    }
    
    private Boolean checkForConflicts(ZonedDateTime stime, ZonedDateTime etime, int aptId){
        Boolean noConflicts = appointmentDBA.checkConflict(stime, etime, aptId);

        if(!noConflicts){
            this.emptyValueAlert("Another appointment is already established for that time.");
        }
        
        return noConflicts;
    }
}