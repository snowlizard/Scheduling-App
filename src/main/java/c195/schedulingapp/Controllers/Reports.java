/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.Models.HelperFunctions;
import c195.schedulingapp.Models.CountryReport;
import c195.schedulingapp.Models.Connector;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    
    private Connector connector = new Connector();

    @FXML private TableView<?> tmTable;
    @FXML private TableColumn<?, ?> tmMonth;
    @FXML private TableColumn<?, ?> tmType;
    @FXML private TableColumn<?, ?> tmTotal;
    @FXML private TableView<CountryReport> ccTable;
    @FXML private TableColumn<CountryReport, String> ccName;
    @FXML private TableColumn<CountryReport, String> ccCountry;
    @FXML private TableColumn<CountryReport, Integer> ccTotal;
    @FXML private ChoiceBox<?> contactBox;
    @FXML private TableView<?> contactTable;
    @FXML private TableColumn<?, ?> aptId;
    @FXML private TableColumn<?, ?> aptTitle;
    @FXML private TableColumn<?, ?> aptDesc;
    @FXML private TableColumn<?, ?> aptLocation;
    @FXML private TableColumn<?, ?> aptType;
    @FXML private TableColumn<?, ?> aptStart;
    @FXML private TableColumn<?, ?> aptEnd;
    @FXML private TableColumn<?, ?> custId;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ccTable.setItems(connector.getCountriesReport());
        ccName.setCellValueFactory(new PropertyValueFactory<> ("Country"));
        ccCountry.setCellValueFactory(new PropertyValueFactory<> ("Division"));
        ccTotal.setCellValueFactory(new PropertyValueFactory<> ("countryDivTotal"));
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
