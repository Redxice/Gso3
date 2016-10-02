/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *Information about a person. contains appointments
 * @author daan
 */
public class Contact {
    private String name;
    private List<Appointment> agenda;
    
    /**
     * 
     * @param name name of person
     */
    public Contact(String name){
        agenda = new ArrayList<Appointment>();
        this.name = name;
    }
    
    /**
     * 
     * @return String of the name of the contact
     */
    public String getName(){
        return name;
    }
    
    /**
     * Adds an appointment to the agenda if not already existent
     * @param a appointment 
     * @return true when adding is succesful
     */
    protected boolean addAppointment(Appointment a){
        if(!agenda.contains(a)){
            while(appointments().hasNext()){
                if(appointments().next().getTimespan().unionWith(a.getTimespan()) != null){
                    return false;
                }
            }
            agenda.add(a);
            return true;
        }
        return false;
    }
    
    /**
     * removes an appointment from agenda when appointment is in agenda.
     * @param a appointment
     */
    protected void removeAppointment(Appointment a){
        if(agenda.contains(a)){
            agenda.remove(a);
        }
    }
    
    /**
     * gives all appointments of this contact
     * @return iteraator of appointments
     */
    public Iterator<Appointment> appointments(){
        return agenda.iterator();
    }
}
