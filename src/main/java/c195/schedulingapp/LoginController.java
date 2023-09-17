/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package c195.schedulingapp;

import java.io.InputStream;
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

import java.sql.Connection;
import java.sql.ResultSet;
/**
 * FXML Controller class
 *
 * @author mrjack
 */
public class LoginController implements Initializable {
    
    @FXML private Text loginError;
    @FXML private Label userLabel;
    @FXML private Label passLabel;
    @FXML private Text zoneLabel;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button login;
    @FXML private ChoiceBox<String> language;
    @FXML private Text timeZone;
    
    private Connection connect;
    
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
        
        if(locale.toString().contains("fr")){
            lang = "french.properties";
            language.setValue("français");
        } else {
            lang = "english.properties";
            language.setValue("English");
        }
        
        connect = new Connector().connect();
        setLocale(lang);
    }
    
    public void changeLocale(){
        String currentLanguage = language.getValue();

        if(currentLanguage.equalsIgnoreCase("English")){
            lang = "english.properties";
        } else {
            lang = "french.properties";
        }
        
        setLocale(lang);
    }
    
    public void login(){
        Boolean found = false;
        String uname = username.getText();
        String pword = password.getText();
        
        String query = "SELECT * FROM Users WHERE User_Name = '" +
                uname + "' AND Password = '" + pword + "';";
        try{
            ResultSet set = connect.prepareStatement(query).executeQuery();
            while(set.next()){
                if(uname.equals(set.getString("User_Name")) 
                        && pword.equals(set.getString("Password"))){
                    found = true;
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        if(found){
            loginError.setVisible(false);
        } else{
            loginError.setVisible(true);
        }
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
