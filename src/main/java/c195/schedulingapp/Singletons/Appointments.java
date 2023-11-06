/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author mrjack
 */
public class Appointments {
    private static final Appointments instance = new Appointments();
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private Appointment appointment;
    
    public Appointments(){}
    
    public static Appointments getInstance(){
        return instance;
    }
    
    public void addAppointment(Appointment apt){
        appointments.add(apt);
    }
    
    public void resetAppointments(){
        this.appointments = FXCollections.observableArrayList();
    }
    
    public ObservableList<Appointment> getAppointments(){
        return appointments;
    }
    
    public Appointment getCurrentAppointment(){
        return this.appointment;
    }
    
    public void setCurrentAppointment(Appointment apt){
        this.appointment = apt;
    }
}
