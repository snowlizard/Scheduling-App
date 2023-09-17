/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp;

import java.io.InputStream;
import java.util.Locale;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author mrjack
 */
public class LoginController implements Initializable {
    
    @FXML private Label userLabel;
    @FXML private Label passLabel;
    @FXML private Text zoneLabel;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button login;
    @FXML private ChoiceBox<String> language;
    @FXML private Text timeZone;
    
    private Properties prop = new Properties();
    private String user;
    private String pass;
    private String log;
    private String zone;
    private String lang;
    
    private static ObservableList<String> languages = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        languages.add("English");
        languages.add("français");
        language.setItems(languages);
        
        Locale locale = Locale.getDefault();
        
        if(locale.toString().contains("en")){
            lang = "english.properties";
            language.setValue("English");
        } else {
            lang = "french.properties";
            language.setValue("français");
        }
        
        setLocale(lang);
    }
    
    public void changeLocale(ActionEvent e){
        String currentLanguage = language.getValue();

        if(currentLanguage.equalsIgnoreCase("English")){
            lang = "english.properties";
        } else {
            lang = "french.properties";
        }
        
        setLocale(lang);
    }
    
    private void setLocale (String propertyFile){
        InputStream stream;
        
        try{
            stream = getClass().getResourceAsStream(propertyFile);
            
            prop.load(stream);
            
            user   = prop.getProperty("username");
            pass   = prop.getProperty("password");
            log    = prop.getProperty("login");
            zone   = prop.getProperty("timezone");
            
            userLabel.setText(user);
            passLabel.setText(pass);
            login.setText(log);
            zoneLabel.setText(zone);
            
        } catch (Exception error){
            System.out.println(error);
        }
    }
}
