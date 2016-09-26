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
 * @author Melvin
 */
public class TimeSpanTest {
    
    public TimeSpanTest() {
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
     * Test of getBeginTime method, of class TimeSpan.
     */
    @Test
    public void testGetBeginTime() {
        System.out.println("getBeginTime");
        Time Begin = new Time(2016,9,19,15,40);
        Time End = new Time(2016,9,19,16,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        ITime expResult = Begin;
        ITime result = instance.getBeginTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getEndTime method, of class TimeSpan.
     */
    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
        ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,16,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        ITime expResult = End;
        ITime result = instance.getEndTime();
        assertEquals(expResult, result);
 
    }

    /**
     * Test of length method, of class TimeSpan.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        Time Begin = new Time(2016,9,19,15,40);
        Time End = new Time(2016,9,19,16,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        int expResult = End.difference(Begin);
        int result = instance.length();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of setBeginTime method, of class TimeSpan.
     */
    @Test
    public void testSetBeginTime() {
        System.out.println("setBeginTime");
        Time Begin = new Time(2016,9,19,15,40);
        Time End = new Time(2016,9,19,16,40);
        Time beginTime = new Time(2016,9,19,14,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        instance.setBeginTime(beginTime);
        assertEquals(beginTime, instance.getBeginTime());

    }

    /**
     * Test of setEndTime method, of class TimeSpan.
     */
    @Test
    public void testSetEndTime() {
        System.out.println("setEndTime");
        ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,16,40);
        ITime endTime = new Time(2016,9,19,18,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        instance.setEndTime(endTime);
       assertEquals(endTime, instance.getEndTime());
    }

    /**
     * Test of move method, of class TimeSpan.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        int minutes = 1;
        ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,18,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        instance.move(minutes);
        ITime exptBegin = new Time(2016,9,19,15,41);
        assertEquals(exptBegin.getMinutes(), instance.getBeginTime().getMinutes());
    }

    /**
     * Test of changeLengthWith method, of class TimeSpan.
     */
    @Test
    public void testChangeLengthWith() {
        System.out.println("changeLengthWith");
        ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,18,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        int minutes = 1;
        instance.changeLengthWith(minutes);
        assertEquals( 41,instance.getEndTime().getMinutes());
    }

    /**
     * Test of isPartOf method, of class TimeSpan.
     */
    @Test
    public void testIsPartOf() {
        System.out.println("isPartOf");
        ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,18,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        ITime IBegin = new Time(2016,9,19,16,40);
        ITime IEnd = new Time(2016,9,19,18,40);
        ITimeSpan timeSpan = new TimeSpan(IBegin,IEnd);;
        boolean expResult = true;
        boolean result = instance.isPartOf(timeSpan);
        assertEquals(expResult, result);
    }

    /**
     * deze test is niet correct
     * Test of unionWith method, of class TimeSpan.
     */
    //
    @Test
    public void testUnionWith() {
        System.out.println("unionWith");
         ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,18,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        ITime IBegin = new Time(2016,9,24,16,40);
        ITime IEnd = new Time(2016,9,25,18,40);
        ITimeSpan timeSpan = new TimeSpan(IBegin,IEnd);;
        ITimeSpan expResult = null;
        ITimeSpan result = instance.unionWith(timeSpan);
        assertEquals(expResult, result);
    }

    /**
     * deze test is niet correct
     * Test of intersectionWith method, of class TimeSpan.
     *///
    @Test
    public void testIntersectionWith() {
        System.out.println("intersectionWith");
        ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,18,40);
        TimeSpan instance = new TimeSpan(Begin,End);
        ITime IBegin = new Time(2016,9,24,16,40);
        ITime IEnd = new Time(2016,9,25,18,40);
        ITimeSpan timeSpan = new TimeSpan(IBegin,IEnd);
        ITimeSpan expResult = null;
        ITimeSpan result = instance.intersectionWith(timeSpan);
        assertEquals(expResult, result);
        
    }
    
}
