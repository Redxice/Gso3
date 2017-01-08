/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrale;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author redxice
 */
public class CentraleBankSetUP
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            BankCentrale centrale = new BankCentrale();
            System.out.println("Centrale bank is up");
        } catch (RemoteException ex)
        {
            Logger.getLogger(CentraleBankSetUP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
