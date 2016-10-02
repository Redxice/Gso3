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
public class ContactTest {
    
    public ContactTest() {
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
     * Test of getName method, of class Contact.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Contact instance = new Contact("Daan");
        String expResult = "Daan";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of addAppointment method, of class Contact.
     */
    @Test
    public void testAddAppointment() {
        System.out.println("addAppointment");
        Appointment a = new Appointment("Vergadering",new TimeSpan(new Time(2016,9,12,1,2),new Time(2016,9,12,5,2)));
        Contact instance = new Contact("Daan");
        boolean expResult = true;
        boolean result = instance.addAppointment(a);
        assertEquals(expResult, result);
        
        expResult = false;
        result = instance.addAppointment(a);
        assertEquals(expResult, result);
        
        
        a = new Appointment("Vergadering",new TimeSpan(new Time(2016,9,12,1,2),new Time(2016,9,12,5,2)));
        expResult = false;
        result = instance.addAppointment(a);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of removeAppointment method, of class Contact.
     */
    @Test
    public void testRemoveAppointment() {
        System.out.println("removeAppointment");
        Appointment a = new Appointment("Vergadering",new TimeSpan(new Time(2016,9,12,1,2),new Time(2016,9,12,5,2)));
        Contact instance = new Contact("Daan");
        instance.addAppointment(a);
        instance.removeAppointment(a);
        if(instance.appointments().hasNext()){
            fail("Still an appointment");
        }
    }

    /**
     * Test of appointments method, of class Contact.
     */
    @Test
    public void testAppointments() {
        System.out.println("appointments");
        Contact instance = new Contact("Daan");
        boolean expResult = instance.appointments().hasNext();
        boolean result = instance.appointments().hasNext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
