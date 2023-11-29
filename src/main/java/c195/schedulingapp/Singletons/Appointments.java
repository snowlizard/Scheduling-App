/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Singletons;

import c195.schedulingapp.Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;
/**
 * Appointment Singleton
 * @author Julian
 */
public class Appointments {
    private static final Appointments instance = new Appointments();
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private Appointment appointment;
    
    public Appointments(){}
    
    /**
     * Gets the instance
     * @return Appointments instance
     */
    public static Appointments getInstance(){
        return instance;
    }
    
    /**
     * Add an appointment to the list of appointments
     * @param apt Appointment
     */
    public void addAppointment(Appointment apt){
        appointments.add(apt);
    }
    
    /**
     * Sets appointments to en empty list
     */
    public void resetAppointments(){
        this.appointments = FXCollections.observableArrayList();
    }
    
    /**
     * Get all appointments
     * @return ObservableList of appointments
     */
    public ObservableList<Appointment> getAppointments(){
        return appointments;
    }
    
    /**
     * get the selected appointment
     * @return Appointment
     */
    public Appointment getCurrentAppointment(){
        return this.appointment;
    }
    
    /**
     * sets the current selected appointment
     * @param apt Appointment
     */
    public void setCurrentAppointment(Appointment apt){
        this.appointment = apt;
    }
    
    /**
     * Get appointments starting in the current month
     * @return List of appointments
     */
    public ObservableList<Appointment> getAppointmentsMonth(){
        LocalDateTime today = LocalDateTime.now();
        ObservableList<Appointment> temp = FXCollections.observableArrayList();
        
        appointments.forEach((apt) -> {
            if(apt.getStart().getMonth() == today.getMonth()){
                temp.add(apt);
            }
        });
        
        return temp;
    }
    
    /**
     * Get appointments starting in the current week
     * @return List of appointments
     */
    public ObservableList<Appointment> getAppointmentsWeek(){
        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance(locale);
        LocalDateTime today = LocalDateTime.now();
        ObservableList<Appointment> temp = FXCollections.observableArrayList();
        
        calendar.set(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        
        int currentWeek = calendar.get(calendar.WEEK_OF_MONTH);
        
        appointments.forEach((apt) -> {
            Calendar c2 = Calendar.getInstance(locale);
            c2.set(apt.getStart().getYear(), apt.getStart().getMonthValue(),
                    apt.getStart().getDayOfMonth());
            int startWeek = c2.get(c2.WEEK_OF_MONTH);
            if(currentWeek == startWeek){
                temp.add(apt);
            }
        });
        
        return temp;
    }
    
    /**
     * Get an appointment by its ID
     * @param aptId Integer
     * @return Appointment if found
     */
    public Appointment getAptById(int aptId){
        Appointment foundApt = null;
        for(Appointment apt: appointments){
            if(apt.getId() == aptId){
                foundApt = apt;
            }
        }
        return foundApt;
    }
}
