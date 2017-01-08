/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrale;


import bank.bankieren.*;
import bank.bankieren.Money;
import fontys.util.NumberDoesntExistException;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.registry.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author redxice
 */
public class BankCentrale extends UnicastRemoteObject implements IBankCentrale
{

    private ArrayList<IBank> banks = new ArrayList<>();
    private Map<Integer, IRekeningTbvBank> accounts = new HashMap<>();
    private int nieuwReknr;
    public BankCentrale() throws RemoteException
    {
           nieuwReknr = 100000000;
           
            //Bind the remote object stub in the registry
            Registry registry = LocateRegistry.createRegistry(666);
        
        try
        {
            registry.bind("central", (IBankCentrale)this);
        } catch (AlreadyBoundException ex)
        {
            Logger.getLogger(BankCentrale.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex)
        {
            Logger.getLogger(BankCentrale.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

    @Override
    public boolean addBank(IBank bank) throws RemoteException
    {   for (IBank b: banks)
        {
            if (b.getName().equals(bank.getName()))
            {
                System.out.println("Return false");
                return false;
            }
        }
        System.out.println("Bank added "+bank);
        banks.add(bank);
        return true;
    }

    @Override
    public boolean MaakOver(int source, int destination, Money money) throws NumberDoesntExistException, RemoteException
    {
        IBank source_Bank = getBank(source);
        IBank dest_Bank = getBank(destination);
        {
            if (source == destination)
            {
                throw new RuntimeException(
                        "cannot transfer money to your own account");
            }
            if (!money.isPositive())
            {
                throw new RuntimeException("money must be positive");
            }

            IRekeningTbvBank source_account = (IRekeningTbvBank) source_Bank.getRekening(source);
            if (source_account == null)
            {
                throw new NumberDoesntExistException("account " + source
                        + " unknown at " + source_Bank.getName());
            }

            Money negative = Money.difference(new Money(0, money.getCurrency()),
                    money);
            boolean success = source_account.muteer(negative);
            if (!success)
            {
                return false;
            }

            IRekeningTbvBank dest_account = (IRekeningTbvBank) dest_Bank.getRekening(destination);
            System.out.println("dest_account"+dest_account);
            if (dest_account == null)
            {
                throw new NumberDoesntExistException("account " + destination
                        + " unknown at " + dest_Bank.getName());
            }
            success = dest_account.muteer(money);

            if (!success) // rollback
            {
                source_account.muteer(money);
            } else
            {
                System.out.println("Succes in maak over");
               source_Bank.InformBank(String.valueOf(source),source_account.getSaldo().getValue());
               dest_Bank.InformBank(String.valueOf(destination),dest_account.getSaldo().getValue());
            }
            return success;
        }
    }

    private IBank getBank(int nr)
    { System.out.println("In get bank");
        for (IBank bank : banks)
        {
            System.out.println("Ik zit hier vast");
            try
            {   
                if (bank.getRekening(nr)!= null)
                {  
                    System.out.println("Ik ben er uit");
                    return bank;  
                }
            } catch (RemoteException ex)
            {
                Logger.getLogger(BankCentrale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       System.out.println("Niks gevonden in de for loop");
      return null;
    }

    @Override
    public int NrOfBanks() throws RemoteException
    {
        return this.banks.size();
    }
    @Override
     public synchronized int GetRekeningNr()
    {
        nieuwReknr++;
        return nieuwReknr-1;
    }

    @Override
    public void addAccount(int nr,IRekeningTbvBank rekening) throws RemoteException
    {
       this.accounts.put(nr, rekening);
    }

   
}
