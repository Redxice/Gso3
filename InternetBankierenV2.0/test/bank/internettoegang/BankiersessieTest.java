/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
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
public class BankiersessieTest {
    
    public BankiersessieTest() {
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
     * Test of isGeldig method, of class Bankiersessie.
     */
    @Test
    public void testIsGeldig() throws RemoteException {
        System.out.println("isGeldig");
        Bankiersessie instance = new Bankiersessie(10000000, new Bank("Rabobank"));
        boolean expResult = true;
        boolean result = instance.isGeldig();
        assertEquals(expResult, result);
    }

    /**
     * Test of maakOver method, of class Bankiersessie.
     */
    @Test
    public void testMaakOver() throws Exception {
        System.out.println("maakOver");
        Bank bank =  new Bank("Rabobank");
        bank.openRekening("Daan", "Tilburg");
        bank.openRekening("Hans", "Tilburg");
        Money bedrag = new Money(10000, "\u20AC");
        Bankiersessie instance = new Bankiersessie(100000000,bank);
        try{
            instance.maakOver(100000000, bedrag);
            fail("its ok");
        }
        catch(RuntimeException ex){}
        bedrag = new Money(-1000, "\u20AC");
        try{
            instance.maakOver(100000001, bedrag);
            fail("oops");
        }catch(RuntimeException ex){}
        
        bedrag = new Money(100,"\u20AC");
        boolean result = instance.maakOver(100000001, bedrag);
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of getRekening method, of class Bankiersessie.
     */
    @Test
    public void testGetRekening() throws Exception {
        System.out.println("getRekening");
        Bank bank = new Bank("Rabobank");
        bank.openRekening("Hans", "Tilburg");
        Bankiersessie instance = new Bankiersessie(100000000, bank);
        int expResult = 100000000;
        int result = instance.getRekening().getNr();
        assertEquals(expResult, result);
    }
    
}
