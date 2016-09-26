/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

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
public class TimeTest {
    
    public TimeTest() {
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
     * Test of getDayInWeek method, of class Time.
     */
    @Test
    public void testGetDayInWeek() {
        System.out.println("getDayInWeek");
        for(int i =4; i < 11; i++){
            Time instance = new Time(2016,9,i,1,2);
        DayInWeek expResult = DayInWeek.values()[i-4];
        DayInWeek result = instance.getDayInWeek();
        
        assertEquals(expResult, result);
        }
    }

    /**
     * Test of getYear method, of class Time.
     */
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        Time instance = new Time(1994,3,27,1,1);
        int expResult = 1994;
        int result = instance.getYear();
        assertEquals(expResult, result);

    }

    /**
     * Test of getMonth method, of class Time.
     */
    @Test
    public void testGetMonth() {
        System.out.println("getMonth");
        Time instance = new Time(1994,3,27,1,1);
        int expResult = 3;
        int result = instance.getMonth();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDay method, of class Time.
     */
    @Test
    public void testGetDay() {
        System.out.println("getDay");
        Time instance = new Time(1994,3,27,1,1);
        int expResult = 27;
        int result = instance.getDay();
        assertEquals(expResult, result);

    }

    /**
     * Test of getHours method, of class Time.
     */
    @Test
    public void testGetHours() {
        System.out.println("getHours");
        Time instance = new Time(1994,3,27,1,1);
        int expResult = 1;
        int result = instance.getHours();
        assertEquals(expResult, result);

    }

    /**
     * Test of getMinutes method, of class Time.
     */
    @Test
    public void testGetMinutes() {
        System.out.println("getMinutes");
        Time instance = new Time(1994,3,27,1,1);
        int expResult = 1;
        int result = instance.getMinutes();
        assertEquals(expResult, result);

    }

    /**
     * Test of plus method, of class Time.
     */
    @Test
    public void testPlus() {
        System.out.println("plus");
        int minutes = 2;
        Time instance = new Time(1994,3,27,1,1);
        ITime expResult = new Time(1994,3,27,1,3);
        ITime result = instance.plus(minutes);
        assertEquals(expResult.getMinutes(), result.getMinutes());

    }

    /**
     * Test of compareTo method, of class Time.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        ITime t = new Time(1994,3,27,1,1);
        Time instance = new Time(1994,3,27,1,2);
        int expResult = -1;
        int result = instance.compareTo(t);
        assertEquals(expResult, result);

    }

    /**
     * Test of difference method, of class Time.
     */
    @Test
    public void testDifference() {
        System.out.println("difference");
        ITime time = new Time(1994,3,27,1,1);
        Time instance = new Time(1994,3,27,1,4);
        int expResult = 3;
        int result = instance.difference(time);
        assertEquals(expResult, result);


    }
    
    @Test
    public void testConstructorHighExceptions(){
        try{
            Time t = new Time(1994,122,27,1,1);
            fail("Wrong month");
        }
        catch(IllegalArgumentException exc){}
        
        try{
            Time t = new Time(1994,5,40,1,1);
            fail("Wrong day");
        }
        catch(IllegalArgumentException exc){}
        
        try{
            Time t = new Time(1994,5,27,60,1);
            fail("Wrong hour");
        }
        catch(IllegalArgumentException exc){}
        
        try{
            Time t = new Time(1994,5,27,1,120);
            fail("Wrong day");
        }
        catch(IllegalArgumentException exc){}
        
        
    }
    
    
    @Test
    public void testConstructorLowExceptions(){
        try{
            Time t = new Time(1994,0,27,1,1);
            fail("Wrong month");
        }
        catch(IllegalArgumentException exc){}
        
        try{
            Time t = new Time(1994,5,-2,1,1);
            fail("Wrong day");
        }
        catch(IllegalArgumentException exc){}
        
        try{
            Time t = new Time(1994,5,27,-5,1);
            fail("Wrong hour");
        }
        catch(IllegalArgumentException exc){}
        
        try{
            Time t = new Time(1994,5,27,1,-10);
            fail("Wrong day");
        }
        catch(IllegalArgumentException exc){}
        
        
    }
}
