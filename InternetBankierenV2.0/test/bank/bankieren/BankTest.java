/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import fontys.util.NumberDoesntExistException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daan_
 */
public class BankTest {
    
    public BankTest() {
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
     * Test of openRekening method, of class Bank.
     */
    @Test
    public void testOpenRekening() {
        System.out.println("openRekening");
        String name = "";
        String city = "";
        Bank instance = new Bank("Rabobank");
        int expResult = -1;
        int result = instance.openRekening(name, city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
        name = "Jan Peter";
        city = "Breda";
        expResult = 100000000;
        result = instance.openRekening(name, city);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getRekening method, of class Bank.
     */
    @Test
    public void testGetRekening() {
        System.out.println("getRekening");
        int nr = 100000000;
        Bank instance = new Bank("Rabobank");
        instance.openRekening("Jan Peter", "Breda");
        String expResult = "Jan Peter";
        String result = instance.getRekening(nr).getEigenaar().getNaam();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test
    public void testMaakOver() throws Exception {
        System.out.println("maakOver");
        int source = 100000000;
        int destination = 100000001;
        
        Bank instance = new Bank("Rabobank");
        
        instance.openRekening("Jan Peters", "Breda"); //100000000
        instance.openRekening("Peter Jansen", "Tilburg"); //100000001
        
        Rekening r = (Rekening)instance.getRekening(100000000);
        
        Money money = new Money(-5000,"\u20AC");
        try{
            instance.maakOver(100000000, 100000000, money);
            fail("destination != source");
        }catch(RuntimeException ex){}
        try{
            instance.maakOver(100000000, 100000001, money);
            fail("negatief geld");
        }catch(RuntimeException ex){}
        money = new Money(5000,"\u20AC");
        try{
            instance.maakOver(100000002, 100000001, money);
            fail("");
        }catch(NumberDoesntExistException ex){}
        
        try{
            instance.maakOver(100000000, 100000003, money);
            fail("");
        }catch(NumberDoesntExistException ex){}
        
        
        
        
        money = new Money(500000000,"\u20AC");
        boolean expResult = false;
        boolean result = instance.maakOver(source, destination, money);
        assertEquals(expResult, result);
     
        
        r.muteer(new Money(1000,"\u20AC"));
        instance.maakOver(100000000, 100000001, money);
        
        
        expResult = false;
        result = instance.maakOver(source, destination, money);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }
    
    
    @Test
    public void testGetExistingKlan(){
        Bank instance = new Bank("Rabobank");
        instance.openRekening("Hans", "Breda");
        instance.openRekening("Hans", "Breda");
    }
    
    @Test
    public void testGetNaam(){
        Bank instance = new Bank("Rabobank");
        
        String expResult = "Rabobank";
        String result = instance.getName();
        
        assertEquals(expResult,result);
    }

    
}
