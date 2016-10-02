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
 * A timespan registered in an agenda 
 * @author daan
 */

public class Appointment {

    
    private List<Contact> invitees;
    private String subject;
    private ITimeSpan timeSpan;

    /**
     * 
     * @param subject description of appointment
     * @param timeSpan time appointment takes
     */
    public Appointment(String subject, ITimeSpan timeSpan) {
        this.subject = subject;
        this.timeSpan = timeSpan;
        invitees = new ArrayList<Contact>();
    }

    public String getSubject() {
        return subject;
    }

    public ITimeSpan getTimespan() {
        return timeSpan;
    }

    /**
     * 
     * @return Iterator of invitees
     */
    public Iterator<Contact> invitees() {
        return invitees.iterator();
    }

    /**
     * Adds contacts if not already existent in invitees list.
     * @param c Contact to be added
     * @return true when contact is added, else false
     */
    public boolean addContact(Contact c) {
        if (!invitees.contains(c)) {
            
            invitees.add(c);
            return true;
        }
        return false;
    }

    /**
     * Removes a contact from invitee list, only if contact is in list.
     * @param c Contact to be removed
     */
    public void removeContact(Contact c) {
        if (invitees.contains(c)) {
            invitees.remove(c);
        }
    }

}
