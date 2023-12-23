/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

import c195.schedulingapp.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Modality;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
/**
 * Helper functions used for setting date time, modals, scene switching
 * @author Julian
 */
public class HelperFunctions {
    private ZoneId utc = ZoneId.of("UTC");
    private ZoneId est = ZoneId.of("America/New_York");
    private ZoneId localZone = ZoneId.systemDefault();
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public HelperFunctions(){}
    
    /**
     * sets the scene
     * @param event ActionEvent
     * @param nextScene String name of next scene
     * @param title String page title
     * @throws IOException 
     */
    public void setScene(ActionEvent event, String nextScene, String title) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(loadFXML(nextScene));
        
        stage.setTitle(title);
        stage.setScene(scene);
    }
    
    /**
     * Opens a model with the given scene
     * @param modal FXML file
     * @throws IOException 
     */
    public void setModal(String modal) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML(modal));
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    /**
     * Loads an FXML file
     * @param fxml
     * @return FXML object
     * @throws IOException 
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * converts the given string into a ZonedDateTime object
     * @param dateTime string date time
     * @return new ZonedDateTime object in local time
     */
    public ZonedDateTime getZDT(String dateTime){
        LocalDateTime tempDT = LocalDateTime.parse(dateTime, format);
        return ZonedDateTime.of(tempDT, localZone);
    }
    
    /**
     * converts the given string into a ZonedDateTime object
     * @param dateTime string date time
     * @return new ZonedDateTime object in local time from UTC
     */
    public ZonedDateTime getZDTFromUTC(String dateTime){
        LocalDateTime tempDT = LocalDateTime.parse(dateTime, format);
        ZonedDateTime zdt = ZonedDateTime.of(tempDT, utc);
        return zdt.withZoneSameInstant(localZone);
    }
    
    /**
     * Converts date from local time to UTC
     * and returns its string value
     * @param localDT ZonedDateTime local
     * @return String
     */
    public String getUTCfromLocal(ZonedDateTime localDT){
        return localDT.withZoneSameInstant(utc).format(format);
    }
    
    public String getUTCfromLocalString(String localDT){
        LocalDateTime tempDT = LocalDateTime.parse(localDT, format);
        ZonedDateTime zdt = ZonedDateTime.of(tempDT, localZone);
        return zdt.withZoneSameInstant(utc).format(format);
    }
    
    /**
     * Checks the given date time to ensure they are within operating hours
     * @param dateTime string date time
     * @return true if within operating hours
     */
    public Boolean checkInTime(String dateTime){
        LocalDateTime tempDT = LocalDateTime.parse(dateTime, format);
        ZonedDateTime localDate = ZonedDateTime.of(tempDT, localZone);
        ZonedDateTime estCheck = localDate.withZoneSameInstant(est);

        if(estCheck.getHour() == 22 && estCheck.getMinute() > 0){
            return false;
        } else if(estCheck.getHour() < 22 && estCheck.getHour() >= 8){
            return true;
        }else{
            return false;
        }
    }
}