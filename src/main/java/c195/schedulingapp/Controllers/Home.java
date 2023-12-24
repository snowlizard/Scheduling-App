/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.DBAccess.appointmentDA;
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
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

/**
 * Home controller; application main menu
 * @author Julian
 */
public class Home implements Initializable {
    @FXML private Label appt_msg;
    
    /**
     * Upon opening the home page look for appointments within the next 15 minutes
     * and update the banner
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        // Look for any appointment within the next 15 minutes
        new appointmentDA().getAppointments().forEach((appointment) -> {
            long timeUntil = now.until(appointment.getStart(), ChronoUnit.MINUTES);
            if(timeUntil <= 15 && timeUntil >= 0){
                String nextApt = "Next Appointment ID: " + appointment.getId() +
                        "\nDate Time: " + appointment.getStart()
                                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
                appt_msg.setText(nextApt);
            }
        });
    }
    
    /**
     * Sends the user back to the login screen
     * @param event ActionEvent
     * @throws IOException 
     */
    public void logout(ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/login", "Scheduling App");
    }
    
    /**
     * Sends the user to the login screen
     * @param event ActionEvent
     * @throws IOException 
     */
    public void appointments(ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/appointments", "Appointments");
    }
    
    /**
     * Sends users to the customers screen
     * @param event ActionEvent
     * @throws IOException 
     */
    public void customers(ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/customers", "Customers");
    }
    
    /**
     * Sends user to the reports screen
     * @param event ActionEvent
     * @throws IOException 
     */
    public void reports(ActionEvent event) throws IOException{
        new HelperFunctions().setScene(event, "/fxml/reports", "Reports");
    }
}
