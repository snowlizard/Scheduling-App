/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.Appointment;
import c195.schedulingapp.Models.Contact;
import c195.schedulingapp.Models.HelperFunctions;
import c195.schedulingapp.Models.CountryReport;
import c195.schedulingapp.Models.TMReport;
import c195.schedulingapp.DBAccess.contactDA;
import c195.schedulingapp.DBAccess.appointmentDA;
import c195.schedulingapp.DBAccess.reportsDA;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * Reports FXML controller
 *
 * @author Julian
 */
public class Reports implements Initializable {
    
    private reportsDA reportDBA = new reportsDA();
    private ObservableList<Contact> contacts = new contactDA().getContacts();
    private ObservableList<Appointment> appointments = new appointmentDA().getAppointments();

    @FXML private TableView<TMReport> tmTable;
    @FXML private TableColumn<TMReport, String> tmMonth;
    @FXML private TableColumn<TMReport, String> tmType;
    @FXML private TableColumn<TMReport, Integer> tmTotal;
    @FXML private TableView<CountryReport> ccTable;
    @FXML private TableColumn<CountryReport, String> ccName;
    @FXML private TableColumn<CountryReport, String> ccCountry;
    @FXML private TableColumn<CountryReport, Integer> ccTotal;
    @FXML private ChoiceBox<String> contactBox;
    @FXML private TableView<Appointment> contactTable;
    @FXML private TableColumn<Appointment, Integer> aptId;
    @FXML private TableColumn<Appointment, String> aptTitle;
    @FXML private TableColumn<Appointment, String> aptDesc;
    @FXML private TableColumn<Appointment, String> aptLocation;
    @FXML private TableColumn<Appointment, String> aptType;
    @FXML private TableColumn<Appointment, String> aptStart;
    @FXML private TableColumn<Appointment, String> aptEnd;
    @FXML private TableColumn<Appointment, Integer> custId;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up countries report
        ccTable.setItems(reportDBA.getCountriesReport());
        ccName.setCellValueFactory(new PropertyValueFactory<> ("Country"));
        ccCountry.setCellValueFactory(new PropertyValueFactory<> ("Division"));
        ccTotal.setCellValueFactory(new PropertyValueFactory<> ("countryDivTotal"));
        
        // Set up appointment month type report
        tmTable.setItems(reportDBA.getTMReport());
        tmMonth.setCellValueFactory(new PropertyValueFactory<> ("Month"));
        tmType.setCellValueFactory(new PropertyValueFactory<> ("Type"));
        tmTotal.setCellValueFactory(new PropertyValueFactory<> ("Total"));
        
        // Contact choicebox
        // Set Contact choices
        ObservableList<String> contactos = FXCollections.observableArrayList();
        
        // Lambda - add each contact name to new contacts list
        contacts.forEach((contacto) -> {
            contactos.add(contacto.getName());
        });
        
        contactBox.setItems(contactos);
    }
    
    /**
     * Sets the appointments table according to the current contact
     */
    public void setAppointmentsReport(){
        int id = new contactDA().getID(contactBox.getValue());
        
        contactTable.setItems(appointments.filtered((apt) -> apt.getContactId() == id));
        aptId.setCellValueFactory(new PropertyValueFactory<> ("id"));
        aptTitle.setCellValueFactory(new PropertyValueFactory<> ("title"));
        aptDesc.setCellValueFactory(new PropertyValueFactory<> ("description"));
        aptType.setCellValueFactory(new PropertyValueFactory<> ("type"));
        aptLocation.setCellValueFactory(new PropertyValueFactory<> ("location"));
        aptStart.setCellValueFactory(new PropertyValueFactory<> ("start"));
        aptEnd.setCellValueFactory(new PropertyValueFactory<> ("end"));
        custId.setCellValueFactory(new PropertyValueFactory<> ("customerId"));
    }
    
    /**
     * Sends user back to the home scene
     * @param event ActionEvent
     * @throws IOException 
     */
    public void previousScene (ActionEvent event) throws IOException {
        new HelperFunctions().setScene(event, "/fxml/home", "Home");
    }
}
