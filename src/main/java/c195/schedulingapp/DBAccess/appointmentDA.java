/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.DBAccess;

import c195.schedulingapp.Models.Appointment;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mrjack
 */
public class appointmentDA extends Connector{
    private static Appointment currentAppointment;
    public appointmentDA(){}
    
    public void setCurrent(Appointment apt){
        currentAppointment = apt;
    }
    
    public Appointment getCurrent(){
        return currentAppointment;
    }
    
    public ObservableList<Appointment> getAppointments(){
        ObservableList<Appointment> apts = FXCollections.observableArrayList();
        String query = "SELECT * FROM appointments";
        try{
            ResultSet set = this.connector.prepareStatement(query).executeQuery();
            while(set.next()){
                String startStr = set.getString("Start");
                String endStr   = set.getString("End");
                String createStr= set.getString("Create_Date");
                String lastUpStr= set.getString("Last_Update");
                
                apts.add(new Appointment(
                                set.getInt("Appointment_ID"),
                                set.getString("Title"),
                                set.getString("Description"),
                                set.getString("Location"),
                                set.getString("Type"),
                                helper.getZDTFromUTC(startStr),
                                helper.getZDTFromUTC(endStr),
                                helper.getZDT(createStr),
                                set.getString("Created_By"),
                                helper.getZDT(lastUpStr),
                                set.getString("Last_Updated_By"),
                                set.getInt("Customer_ID"),
                                set.getInt("User_ID"),
                                set.getInt("Contact_ID")));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return apts;
    }
    
    public void insertAppointment(Appointment apt){
        String query = "INSERT INTO appointments "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
      
        try {
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setNull(1, java.sql.Types.NULL);
            pStatement.setString(2, apt.getTitle());
            pStatement.setString(3,apt.getDescription());
            pStatement.setString(4,apt.getLocation());
            pStatement.setString(5, apt.getType());
            pStatement.setString(6,helper.getUTCfromLocal(apt.getStart()));
            pStatement.setString(7,helper.getUTCfromLocal(apt.getEnd()));
            pStatement.setString(8, helper.getUTCfromLocal(apt.getCreateDate()));
            pStatement.setString(9,apt.getCreatedBy());
            pStatement.setString(10,helper.getUTCfromLocal(apt.getLastUpdate()));
            pStatement.setString(11, apt.getLastUpdatedBy());
            pStatement.setInt(12, apt.getCustomerId());
            pStatement.setInt(13, apt.getUserId());
            pStatement.setInt(14, apt.getContactId());
            
            pStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void updateAppointment(Appointment apt){
        String query = "UPDATE appointments "
                + "SET Title = ?, Description = ?, Location = ?, "
                + "Type = ?, Start = ?, End = ?, Create_Date = ?, "
                + "Created_By = ?, Last_Update = ?, Last_Updated_By = ?, "
                + "Customer_ID = ?, User_ID = ?, Contact_ID = ?"
                + "WHERE Appointment_ID = ?;";

        try {
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.setString(1, apt.getTitle());
            pStatement.setString(2, apt.getDescription());
            pStatement.setString(3, apt.getLocation());
            pStatement.setString(4, apt.getType());
            pStatement.setString(5, helper.getUTCfromLocal(apt.getStart()));
            pStatement.setString(6, helper.getUTCfromLocal(apt.getEnd()));
            pStatement.setString(7, helper.getUTCfromLocal(apt.getCreateDate()));
            pStatement.setString(8, apt.getCreatedBy());
            pStatement.setString(9,helper.getUTCfromLocal(apt.getLastUpdate()));
            pStatement.setString(10, apt.getLastUpdatedBy());
            pStatement.setInt(11, apt.getCustomerId());
            pStatement.setInt(12, apt.getUserId());
            pStatement.setInt(13, apt.getContactId());
            pStatement.setInt(14, apt.getId());
            
            pStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void removeAppointment(int appointment_id){
        String query = "DELETE FROM appointments " +
                        "WHERE Appointment_ID = ?;";
        try {
            PreparedStatement pStatement = connector.prepareStatement(query);
            pStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Get appointments starting in the current month
     * @return List of appointments
     */
    public ObservableList<Appointment> getAppointmentsMonth(){
        ObservableList<Appointment> appointments = this.getAppointments();
        
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
        ObservableList<Appointment> appointments = this.getAppointments();
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
        ObservableList<Appointment> appointments = this.getAppointments();
        Appointment foundApt = null;
        for(Appointment apt: appointments){
            if(apt.getId() == aptId){
                foundApt = apt;
            }
        }
        return foundApt;
    }
}
