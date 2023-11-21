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
 *
 * @author mrjack
 */
public class HelperFunctions {
    private ZoneId utc = ZoneId.of("UTC");
    private ZoneId est = ZoneId.of("America/New_York");
    private ZoneId localZone = ZoneId.systemDefault();
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public HelperFunctions(){}
    
    public void setScene(ActionEvent event, String nextScene, String title) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(loadFXML(nextScene));
        
        stage.setTitle(title);
        stage.setScene(scene);
    }
    
    
    public void setModal(String modal) throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(loadFXML(modal));
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public ZonedDateTime getZDT(String dateTime){
        LocalDateTime tempDT = LocalDateTime.parse(dateTime, format);
        return ZonedDateTime.of(tempDT, localZone);
    }

    public ZonedDateTime getZDTFromUTC(String dateTime){
        LocalDateTime tempDT = LocalDateTime.parse(dateTime, format);
        ZonedDateTime zdt = ZonedDateTime.of(tempDT, utc);
        return zdt.withZoneSameInstant(localZone);
    }
    
    public Boolean checkInTime(String dateTime){
        LocalDateTime tempDT = LocalDateTime.parse(dateTime, format);
        ZonedDateTime localDate = ZonedDateTime.of(tempDT, localZone);
        ZonedDateTime estCheck = localDate.withZoneSameInstant(est);

        if(estCheck.getHour() == 17 && estCheck.getMinute() > 0){
            return false;
        } else if(estCheck.getHour() < 17 && estCheck.getHour() >= 8){
            return true;
        }else{
            return false;
        }
    }
}

/**
        ZonedDateTime z = ZonedDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZoneId zoneId = ZoneId.of("UTC");
        ZoneId pst    = ZoneId.of("America/Los_Angeles");
        
        String date = "2023-10-30 19:30";
        LocalDateTime np = LocalDateTime.parse(date, format);
        ZonedDateTime nz = ZonedDateTime.of(np, zoneId);
        
        System.out.println(nz.withZoneSameInstant(pst).format(format));
 */