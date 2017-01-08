/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrale;

import bank.bankieren.Bank;
import bank.bankieren.IBank;
import bank.bankieren.IRekeningTbvBank;
import bank.bankieren.Money;
import fontys.util.NumberDoesntExistException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author redxice
 */
public class BankCentrale extends UnicastRemoteObject implements IBankCentrale
{

    private ArrayList<IBank> banks = new ArrayList<>();

    public BankCentrale() throws RemoteException
    {

    }

    @Override
    public void addBank(IBank bank) throws RemoteException
    {
        banks.add(bank);
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
               source_Bank.InformBank(String.valueOf(source),source_account.getSaldo());
               dest_account.InformBank(String.valueOf(destination),dest_account.getSaldo());
            }
            return success;
        }
    }

    private IBank getBank(int nr)
    {
        for (IBank bank : banks)
        {
            try
            {
                if (bank.getRekening(nr).getNr() == nr)
                {
                     return bank;
                }
            } catch (RemoteException ex)
            {
                Logger.getLogger(BankCentrale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      return null;
    }
}
