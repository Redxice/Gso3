/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daan
 */
public class AppointmentTest {
    
    public AppointmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSubject method, of class Appointment.
     */
    @Test
    public void testGetSubject() {
        System.out.println("getSubject");
        Appointment instance = new Appointment("Vergadering",new TimeSpan(new Time(2016,9,12,1,2),new Time(2016,9,12,5,2)));
        String expResult = "Vergadering";
        String result = instance.getSubject();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimespan method, of class Appointment.
     */
    @Test
    public void testGetTimespan() {
        System.out.println("getTimespan");
        Appointment instance = new Appointment("Vergadering",new TimeSpan(new Time(2016,9,12,1,2),new Time(2016,9,12,5,2)));
        int expResult = 1;
        int result = instance.getTimespan().getBeginTime().getHours();
        assertEquals(expResult, result);
    }

    /**
     * Test of invitees method, of class Appointment.
     */
    @Test
    public void testInvitees() {
        System.out.println("invitees");
        Appointment instance = new Appointment("Vergadering",new TimeSpan(new Time(2016,9,12,1,2),new Time(2016,9,12,5,2)));
        boolean expResult = false;
        boolean result = instance.invitees().hasNext();
        assertEquals(expResult, result);
    }

    /**
     * Test of addContact method, of class Appointment.
     */
    @Test
    public void testAddContact() {
        System.out.println("addContact");
        Contact c = new Contact("Daan");
        Appointment instance = new Appointment("Vergadering",new TimeSpan(new Time(2016,9,12,1,2),new Time(2016,9,12,5,2)));
        boolean expResult = true;
        boolean result = instance.addContact(c);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.addContact(c);
         assertEquals(expResult, result);
    }

    /**
     * Test of removeContact method, of class Appointment.
     */
    @Test
    public void testRemoveContact() {
        System.out.println("removeContact");
        Contact c = new Contact("Daan");
        Appointment instance = new Appointment("Vergadering",new TimeSpan(new Time(2016,9,12,1,2),new Time(2016,9,12,5,2)));
        instance.addContact(c);
        instance.removeContact(c);
        if(instance.invitees().hasNext()){
            fail("contact still there");
        }

    }
    
}
