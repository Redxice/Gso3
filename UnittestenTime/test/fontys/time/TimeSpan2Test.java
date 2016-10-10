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
public class TimeSpan2Test {
    
    public TimeSpan2Test() {
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
 
    @Test
    public void testAllExceptions(){
        try{
        Time end = new Time(2016,9,19,15,40);
        Time begin = new Time(2016,9,19,16,40);
        TimeSpan2 timespan = new TimeSpan2(begin,end);
        fail("Constructor failed");
        }
        catch(IllegalArgumentException ex) {   
        }
        try{
        Time Begin = new Time(2016,9,19,15,40);
        Time End = new Time(2016,9,19,16,40);
        TimeSpan2 instance = new TimeSpan2(Begin,End); 
        instance.setBeginTime(new Time(2016,9,20,16,40));
        fail("setBegin ex failed");
        }
        catch(IllegalArgumentException ex){
        }
        try{
        Time Begin = new Time(2016,9,19,15,40);
        Time End = new Time(2016,9,19,16,40);
        TimeSpan2 instance = new TimeSpan2(Begin,End); 
        instance.setEndTime(new Time(2016,9,18,16,40));
        fail("setEnd failed");
        }
        catch(IllegalArgumentException ex){
            
        }
        try{
        Time Begin = new Time(2016,9,19,15,40);
        Time End = new Time(2016,9,19,16,40);
        TimeSpan2 instance = new TimeSpan2(Begin,End); 
        instance.changeLengthWith(-6);
        fail("cahangeLengthWith failed");
        }
         catch(IllegalArgumentException ex){
            
        }
    }
    @Test
    public void testGetBeginTime() {
        System.out.println("getBeginTime");
        Time Begin = new Time(2016,9,19,15,40);
        Time End = new Time(2016,9,19,16,40);
        TimeSpan2 instance = new TimeSpan2(Begin,End);
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
        TimeSpan2 instance = new TimeSpan2(Begin,End);
        ITime expResult = End;
        ITime result = instance.getEndTime();
        if (expResult.compareTo(result)==0) {
            
        }
        else{
            fail("EndTimes is niet correct");
        }
 
    }

    /**
     * Test of length method, of class TimeSpan.
     */
    @Test
    public void testLength() {
        System.out.println("length");
        Time Begin = new Time(2016,9,19,15,40);
        Time End = new Time(2016,9,19,16,40);
        TimeSpan2 instance = new TimeSpan2(Begin,End);
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
        TimeSpan2 instance = new TimeSpan2(Begin,End);
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
        ITime endTime = new Time(2016,9,20,18,40);
        TimeSpan2 instance = new TimeSpan2(Begin,End);
        instance.setEndTime(endTime);
      
        if (instance.getEndTime().compareTo(endTime)==0) {
            
        }
        else{
            fail("testSetEndTime failed");
        }
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
        TimeSpan2 instance = new TimeSpan2(Begin,End);
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
        TimeSpan2 instance = new TimeSpan2(Begin,End);
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
        //test 1
        ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,18,40);
        TimeSpan2 instance = new TimeSpan2(Begin,End);
        ITime IBegin = new Time(2016,9,19,16,40);
        ITime IEnd = new Time(2016,9,19,17,40);
        TimeSpan2 timeSpan = new TimeSpan2(IBegin,IEnd);;
        boolean expResult = true;
        boolean result = instance.isPartOf(timeSpan);
        assertEquals(expResult, result);
        //test 2
         Begin = new Time(2016,9,18,15,40);
         End = new Time(2016,9,19,18,40);
         instance = new TimeSpan2(Begin,End);
         IBegin = new Time(2016,9,18,15,40);
         IEnd = new Time(2016,9,19,18,40);
         timeSpan = new TimeSpan2(IBegin,IEnd);;
         expResult = true;
         result = instance.isPartOf(timeSpan);
         assertEquals(expResult, result);
         //test3
         Begin = new Time(2016,9,18,15,40);
         End = new Time(2016,9,19,18,40);
         instance = new TimeSpan2(Begin,End);
        IBegin = new Time(2016,9,18,15,40);
         IEnd = new Time(2016,9,20,18,40);
         timeSpan = new TimeSpan2(IBegin,IEnd);;
         expResult = false;
         result = instance.isPartOf(timeSpan);
         assertEquals(expResult, result);
         //test4
         Begin = new Time(2016,9,18,15,40);
         End = new Time(2016,9,19,18,40);
         instance = new TimeSpan2(Begin,End);
         IBegin = new Time(2016,9,9,16,40);
         IEnd = new Time(2016,9,20,18,40);
         timeSpan = new TimeSpan2(IBegin,IEnd);;
         expResult = false;
         result = instance.isPartOf(timeSpan);
         assertEquals(expResult, result);
    }

    /**
     * 
     * Test of unionWith method, of class TimeSpan.
     */
    //
    @Test
    public void testUnionWith() {
        System.out.println("unionWith");
        
        
         ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,18,40);
        TimeSpan2 instance = new TimeSpan2(Begin,End);
        ITime IBegin = new Time(2016,9,24,16,40);
        ITime IEnd = new Time(2016,9,24,18,40);
        TimeSpan2 timeSpan = new TimeSpan2(IBegin,IEnd);;
        ITimeSpan expResult = null;
        ITimeSpan result = instance.unionWith(timeSpan);
        assertEquals(expResult, result);
        
         Begin = new Time(2016,9,19,15,40);
         End = new Time(2016,9,19,18,40);
         instance = new TimeSpan2(Begin,End);
         IBegin = new Time(2016,9,16,16,40);
         IEnd = new Time(2016,9,16,18,40);
         timeSpan = new TimeSpan2(IBegin,IEnd);;
         expResult = null;
         result = instance.unionWith(timeSpan);
        assertEquals(expResult, result);
        
         Begin = new Time(2016,9,19,15,40);
         End = new Time(2016,9,19,18,40);
         instance = new TimeSpan2(Begin,End);
         IBegin = new Time(2016,9,17,16,40);
         IEnd = new Time(2016,9,20,17,40);
         timeSpan = new TimeSpan2(IBegin,IEnd);;
         expResult = new TimeSpan(Begin,End);
         result = instance.unionWith(timeSpan);
         assertEquals(expResult.getBeginTime().getMinutes(), result.getBeginTime().getMinutes());
        
           Begin = new Time(2016,9,9,15,40);
         End = new Time(2016,9,20,18,40);
         instance = new TimeSpan2(Begin,End);
         IBegin = new Time(2016,9,10,16,40);
         IEnd = new Time(2016,9,18,17,40);
         timeSpan = new TimeSpan2(IBegin,IEnd);;
         expResult = timeSpan;
         result = instance.unionWith(timeSpan);
         assertEquals(expResult.getBeginTime().getMinutes(), result.getBeginTime().getMinutes());
        
        
    }

    /**
     * 
     * Test of intersectionWith method, of class TimeSpan.
     *///
    @Test
    public void testIntersectionWith() {
        System.out.println("intersectionWith");
        ITime Begin = new Time(2016,9,19,15,40);
        ITime End = new Time(2016,9,19,18,40);
        TimeSpan2 instance = new TimeSpan2(Begin,End);
        ITime IBegin = new Time(2016,9,24,16,40);
        ITime IEnd = new Time(2016,9,25,18,40);
        TimeSpan2 timeSpan = new TimeSpan2(IBegin,IEnd);
        TimeSpan2 expResult = new TimeSpan2(Begin,IEnd);
        ITimeSpan result = instance.intersectionWith(timeSpan);
        if (expResult.getEndTime().compareTo(result.getEndTime())==0 && expResult.getBeginTime().compareTo(result.getBeginTime())==0) {
        } 
        else {
            fail("IntersectionWith incorrect");
        }

        
         Begin = new Time(2016,9,19,15,40);
         End = new Time(2016,9,19,18,40);
         instance = new TimeSpan2(Begin,End);
         IBegin = new Time(2016,9,17,16,40);
         IEnd = new Time(2016,9,25,18,40);
         timeSpan = new TimeSpan2(IBegin,IEnd);
         expResult = timeSpan;
         result = instance.intersectionWith(timeSpan);
       
          if (expResult.getEndTime().compareTo(result.getEndTime())==0 && expResult.getBeginTime().compareTo(result.getBeginTime())==0) {
        } 
        else {
            fail("IntersectionWith incorrect");
        }
          
         Begin = new Time(2016,9,17,15,40);
         End = new Time(2016,9,26,18,40);
         instance = new TimeSpan2(Begin,End);
         IBegin = new Time(2016,9,19,16,40);
         IEnd = new Time(2016,9,24,18,40);
         timeSpan = new TimeSpan2(IBegin,IEnd);
         expResult = instance;
         result = instance.intersectionWith(timeSpan);
          if (expResult.getEndTime().compareTo(result.getEndTime())==0 && expResult.getBeginTime().compareTo(result.getBeginTime())==0) {
        } 
        else {
            fail("IntersectionWith incorrect");
        }
         
    }

}
