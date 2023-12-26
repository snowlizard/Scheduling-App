/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp.Controllers;

import c195.schedulingapp.App;
import c195.schedulingapp.DBAccess.userDA;
import c195.schedulingapp.Models.HelperFunctions;

import java.io.InputStream;
import java.io.File;
import java.time.ZoneId;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
/**
 * Login page controller
 * @author Julian
 */
public class Login implements Initializable {
    
    @FXML private Text loginError;
    @FXML private Label userLabel;
    @FXML private Label passLabel;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button login;
    @FXML private ChoiceBox<String> language;
    @FXML private Label timeZone;
    
    private userDA userDBA = new userDA();
    
    //private Properties prop = new Properties();
    private ZoneId local = ZoneId.systemDefault();
    private String lang;
    
    private static ObservableList<String> languages = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        languages.add("English");
        languages.add("français");
        language.setItems(languages);
        
        Locale locale = Locale.getDefault();
        
        if(locale.toString().contains("fr")){
            lang = "french.properties";
            language.setValue("français");
        } else {
            lang = "english.properties";
            language.setValue("English");
        }

        setLocale(lang);
        timeZone.setText(local.toString());
    }
    
    /**
     * Changes the language from english to french and vice versa
     */
    public void changeLocale(){
        String currentLanguage = language.getValue();

        if(currentLanguage.equalsIgnoreCase("English")){
            lang = "english.properties";
        } else {
            lang = "french.properties";
        }
        
        setLocale(lang);
    }
    
    /**
     * Logs in the entered user if they
     * enter the correct credentials. Logs successful and unsuccessful login attempts
     * @param event
     * @throws IOException 
     */
    public void login(ActionEvent event) throws IOException {
        Boolean found = false;
        String uname = username.getText();
        String pword = password.getText();
        
        try{
            File file = new File("login_activity.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            
            found = userDBA.validLogin(uname, pword);
            
            // Write to activities file
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            FileWriter fwriter = new FileWriter(file, true);
            fwriter.write("Username: " + uname + " Date: " + timestamp 
                    + " Attempt successful: " + found.toString() + "\n");
            fwriter.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(found){
            loginError.setVisible(false);
            userDBA.setLoggedinUser(uname);
            new HelperFunctions().setScene(event, "/fxml/home", "Home");
        } else{
            loginError.setVisible(true);
        }
    }
    
    /**
     * Sets the language based on the given property file
     * @param propertyFile name of the language properties file
     */
    private void setLocale (String propertyFile){
        try{
            InputStream stream = App.class.getResourceAsStream(propertyFile);
            
            Properties prop = new Properties();
            prop.load(stream);
            
            String user   = prop.getProperty("username", "d");
            String pass   = prop.getProperty("password", "");
            String log    = prop.getProperty("login", "");
            String zone   = prop.getProperty("timezone", "");
            String errorM = prop.getProperty("incorrect", "");
            
            userLabel.setText(user);
            passLabel.setText(pass);
            login.setText(log);
            loginError.setText(errorM);
            
        } catch (Exception error){
            System.out.println(error);
        }
    }
}
