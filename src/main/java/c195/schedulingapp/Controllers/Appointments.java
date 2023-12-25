/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Appointment;
import c195.schedulingapp.DBAccess.appointmentDA;
import c195.schedulingapp.Models.HelperFunctions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Appointment controller, displays all appointments
 * @author Julian
 */
public class Appointments implements Initializable {

    @FXML private TableView<Appointment> appointments;
    @FXML private TableColumn<Appointment, Integer> cId;
    @FXML private TableColumn<Appointment, String> cTitle;
    @FXML private TableColumn<Appointment, String> cDesc;
    @FXML private TableColumn<Appointment, String> cLocation;
    @FXML private TableColumn<Appointment, String> cType;
    @FXML private TableColumn<Appointment, String> cStart;
    @FXML private TableColumn<Appointment, String> cEnd;
    @FXML private TableColumn<Appointment, Integer> cCustId;
    @FXML private TableColumn<Appointment, Integer> cUserId;
    @FXML private TableColumn<Appointment, Integer> cContactId;
    @FXML private ToggleGroup AptFilter = new ToggleGroup();
    @FXML private RadioButton all;
    @FXML private RadioButton month;
    @FXML private RadioButton week;
    @FXML private Button add;
    @FXML private Button modify;
    @FXML private Button delete;
    
    appointmentDA appointmentDBA = new appointmentDA();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
        
        // add to toggle group
        all.setToggleGroup(AptFilter);
        month.setToggleGroup(AptFilter);
        week.setToggleGroup(AptFilter);
        
        AptFilter.selectToggle(all);
    }
    
    private void updateTable(){
        // populate column values
        cId.setCellValueFactory(new PropertyValueFactory<> ("id"));
        cTitle.setCellValueFactory(new PropertyValueFactory<> ("title"));
        cDesc.setCellValueFactory(new PropertyValueFactory<> ("description"));
        cLocation.setCellValueFactory(new PropertyValueFactory<> ("location"));
        cType.setCellValueFactory(new PropertyValueFactory<> ("type"));
        cStart.setCellValueFactory(new PropertyValueFactory<> ("start"));
        cEnd.setCellValueFactory(new PropertyValueFactory<> ("end"));
        cCustId.setCellValueFactory(new PropertyValueFactory<> ("customerId"));
        cUserId.setCellValueFactory(new PropertyValueFactory<> ("userId"));
        cContactId.setCellValueFactory(new PropertyValueFactory<> ("contactId"));

        // Set tablecolumn items
        appointments.setItems(appointmentDBA.getAppointments());
    }
    
    /**
     * Sends the user back to the previous screen without saving any changes made
     * @param event
     * @throws IOException 
     */
    public void previousScene (ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/home", "Home");
    }
    
    /**
     * Opens the appointment form with the current selected appointment
     * @throws IOException 
     */
    @FXML
    private void modifyAppointment() throws IOException {
        Appointment apt = appointments.getSelectionModel().getSelectedItem();
        
        if(apt != null){
            appointmentDBA.setCurrent(apt);
            new HelperFunctions().setModal("/fxml/appointmentForm");
            updateTable();
        }else{
            Alert dialog = new Alert(Alert.AlertType.ERROR, 
                "Select a column to edit an appointment.",
                ButtonType.OK);
            dialog.showAndWait();
        }
    }
    
    /**
     * Opens the appointment form for a new appointment
     * @throws IOException 
     */
    @FXML
    private void newAppointment() throws IOException {
        appointmentDBA.setCurrent(null);
        new HelperFunctions().setModal("/fxml/appointmentForm");
        updateTable();
    }
    
    /**
     * Controls the filter between all, month, and week
     */
    @FXML
    private void toggleFilter(){
        RadioButton toggle = (RadioButton) AptFilter.getSelectedToggle();
        String toggleValue = toggle.getText();
        
        if(toggleValue.equals("All")){
            appointments.setItems(appointmentDBA.getAppointments());
        } else if (toggleValue.equals("Month")){
            appointments.setItems(appointmentDBA.getAppointmentsMonth());
        } else if (toggleValue.equals("Week")){
            appointments.setItems(appointmentDBA.getAppointmentsWeek());
        }
    }
    
    /**
     * Deletes the currently selected appointment on confirmation
     */
    @FXML
    private void deleteAppointment() {
        Appointment apt = appointments.getSelectionModel().getSelectedItem();
        if(apt != null){
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this appointment?\n" +
                "Appoinment ID: " + apt.getId() + ", Type: " + apt.getType(),
                   ButtonType.YES, ButtonType.NO);
            confirm.showAndWait();
            
            if(confirm.getResult() == ButtonType.YES){
                appointmentDBA.removeAppointment(apt.getId());
                updateTable();
            }
        }else{
            Alert dialog = new Alert(Alert.AlertType.ERROR, 
                "Select a column to delete an appointment.",
                ButtonType.OK);
            dialog.showAndWait();
        }
    }
}
