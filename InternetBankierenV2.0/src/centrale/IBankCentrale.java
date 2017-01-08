/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrale;

import bank.bankieren.IBank;
import bank.bankieren.IRekening;
import bank.bankieren.IRekeningTbvBank;
import bank.bankieren.Money;
import fontys.util.NumberDoesntExistException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author redxice
 */
public interface IBankCentrale extends Remote
{
    /**
     * Deze methode voegt de bank toe aan de lijst van de BankCentrale
     * @param bank
     * @throws RemoteException 
     */
    boolean addBank(IBank bank)throws RemoteException;
    /**
     * In deze methode wordt gezocht bij welke bank de bron en bestemming hoort.
     * Dit wordt gedaan door de getRekening Methode aan te roepen van de IBank.
     * @param bron
     * @param bestemming
     * @param bedrag
     * @throws NumberDoesntExistException
     * @throws RemoteException 
     */
    boolean MaakOver(int bron, int bestemming, Money bedrag)
            throws NumberDoesntExistException,RemoteException;
    int NrOfBanks()throws RemoteException;
    int GetRekeningNr()throws RemoteException;
    void addAccount(int nr,IRekeningTbvBank account)throws RemoteException;
}
