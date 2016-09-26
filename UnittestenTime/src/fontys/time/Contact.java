/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.List;

/**
 *
 * @author daan
 */
public class Contact {
    private String name;
    private List<Appointment> agenda;
    
    public Contact(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    private boolean addAppointment(Appointment a){
        return false;
    }
    
}
