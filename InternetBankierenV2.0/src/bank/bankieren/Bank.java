package bank.bankieren;

import centrale.IBankCentrale;
import fontys.util.*;
import internetbankierenv2.IRemotePropertyListener;
import internetbankierenv2.RemotePublisher;
import java.rmi.AccessException;
import java.rmi.NotBoundException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank extends UnicastRemoteObject implements IBank
{

    /**
     *
     */
    private static final long serialVersionUID = -8728841131739353765L;
    private Map<Integer, IRekeningTbvBank> accounts;
    private Collection<IKlant> clients;
    private int nieuwReknr;
    private String name;
    private RemotePublisher remotePublisher;

    public Bank(String name) throws RemoteException
    {
        accounts = new HashMap<Integer, IRekeningTbvBank>();
        clients = new ArrayList<IKlant>();
        nieuwReknr = 100000000;
        this.name = name;
        try
        {
            remotePublisher = new RemotePublisher();
        } catch (RemoteException ex)
        {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized int openRekening(String name, String city) throws RemoteException
    {
        if (name.equals("") || city.equals(""))
        {
            return -1;
        }

        IKlant klant = getKlant(name, city);
        Registry register = LocateRegistry.getRegistry("127.0.0.1", 666);
        IBankCentrale centrale = null;
        try
        {
            centrale = (IBankCentrale) register.lookup("central");
            nieuwReknr = centrale.GetRekeningNr();
        } catch (NotBoundException ex)
        {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex)
        {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("nieuwReknr " + nieuwReknr);
        IRekeningTbvBank account = new Rekening(nieuwReknr, klant, Money.EURO);
        accounts.put(nieuwReknr, account);
        try
        {
            remotePublisher.registerProperty(String.valueOf(nieuwReknr));
           System.out.println("Registerd property "+String.valueOf(nieuwReknr));
        } catch (RemoteException ex)
        {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("accounts " + accounts);
        return nieuwReknr;
    }

    private IKlant getKlant(String name, String city)
    {
        for (IKlant k : clients)
        {
            if (k.getNaam().equals(name) && k.getPlaats().equals(city))
            {
                return k;
            }
        }
        IKlant klant = new Klant(name, city);
        clients.add(klant);
        return klant;
    }

    @Override
    public synchronized IRekeningTbvBank getRekening(int nr) throws RemoteException
    {
        System.out.println("Get rekening bank "+this.accounts.get(nr));
        return this.accounts.get(nr);
    }

    @Override
    public synchronized boolean maakOver(int source, int destination, Money money)
            throws NumberDoesntExistException
    {

        try
        {
            Registry register = LocateRegistry.getRegistry("127.0.0.1", 666);
            IBankCentrale centrale = (IBankCentrale) register.lookup("central");
            return centrale.MaakOver(source, destination, money);
        } catch (RemoteException ex)
        {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex)
        {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException
    {   System.out.println("Een subscriber"+listener+" "+property);
        remotePublisher.subscribeRemoteListener(listener, property);
    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException
    {
        remotePublisher.unsubscribeRemoteListener(listener, property);
    }

    @Override
    public void InformBank(String RekeningNr, String value) throws RemoteException
    {
        remotePublisher.inform(RekeningNr, null, value);
    }

}
