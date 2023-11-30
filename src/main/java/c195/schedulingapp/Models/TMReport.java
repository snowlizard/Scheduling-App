/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c195.schedulingapp.Models;

/**
 * Customers Appointments by type month
 * @author Julian
 */
public class TMReport {
    private String month;
    private String type;
    private int total;
    
    public TMReport(String mes, String tipo, int sum){
        this.month = mes;
        this.type = tipo;
        this.total = sum;
    }
    
    public String getMonth(){
        return this.month;
    }
    
    public String getType(){
        return this.type;
    }
    
    public int getTotal(){
        return this.total;
    }
    
    public void setMonth(String mes){
        this.month = mes;
    }
    
    public void setType(String tipo){
        this.type = tipo;
    }
    
    public void setTotal(int sum){
        this.total = sum;
    }
}
