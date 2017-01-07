/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import java.rmi.RemoteException;
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
public class BalieTest {
    
    public BalieTest() {
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
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekening() throws RemoteException {
        Bank b = new Bank("Rabobank");
        String naam = "";
        String plaats = "";
        String wachtwoord = "";
        Balie instance = new Balie(b);
        String expResult = null;
        String result = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals(expResult, result);
        
        naam = "Henk";
        result = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals(expResult,result);
        plaats = "Henk";
        result = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals(expResult,result);
        wachtwoord = "123456789";
        result = instance.openRekening(naam, plaats, wachtwoord);
        assertEquals(expResult,result);
        wachtwoord = "12345678";
        result = instance.openRekening(naam, plaats, wachtwoord);           
        System.out.println(result);
        
        
    }

    /**
     * Test of logIn method, of class Balie.
     */
    @Test
    public void testLogIn() throws Exception {
        Bank b = new Bank("Rabobank");
        Balie instance = new Balie(b);
        String id = instance.openRekening("Henk", "Breda", "1234567");
        System.out.println("logIn");
        
        IBankiersessie expResult = null;
        IBankiersessie result = instance.logIn("onzin", "1234567");
        assertEquals(expResult, result);
        result = instance.logIn(id,"123456");
        assertEquals(expResult,result);
        result = instance.logIn(id, "1234567");
        assertNotNull(result);

    }
    
}
