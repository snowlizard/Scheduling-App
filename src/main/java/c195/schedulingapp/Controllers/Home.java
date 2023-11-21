/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Singletons.Appointments;
import c195.schedulingapp.Models.HelperFunctions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
/**
 * FXML Controller class
 *
 * @author mrjack
 */
public class Home implements Initializable {
    @FXML private Label appt_msg;
    private Appointments aptsInstance = Appointments.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        // Look for any appointment within the next 15 minutes
        aptsInstance.getAppointments().forEach((appointment) -> {
            if(appointment.getStart().until(now, ChronoUnit.MINUTES) <= 15){
                String nextApt = "Next Appointment: " + appointment.getId() +
                        " " + appointment.getStart().format(DateTimeFormatter.ISO_DATE);
                appt_msg.setText(nextApt);
            }
        });
    }
    
    public void logout(ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/login", "Scheduling App");
    }
    
    public void appointments(ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/appointments", "Appointments");
    }
    
    public void customers(ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/customers", "Customers");
    }
}
