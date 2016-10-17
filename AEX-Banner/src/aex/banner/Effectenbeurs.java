/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author redxice
 */
public class Effectenbeurs extends UnicastRemoteObject implements IEffectenbeurs {

    private List<IFonds> fondsen = new ArrayList<>();
    Random RD = new Random();
    

    public Effectenbeurs() throws RemoteException {
        IFonds Shell = new Fonds("Shell",RD.nextDouble());
        IFonds Unilever = new Fonds("Unilever",RD.nextDouble());
        IFonds KLM = new Fonds("KLM",RD.nextDouble());
        IFonds Philips = new Fonds("Philips",RD.nextDouble());
        IFonds Heineken = new Fonds("Heineken",RD.nextDouble());

         fondsen.add(Shell);
         fondsen.add(Unilever);
         fondsen.add(KLM);
         fondsen.add(Philips);
         fondsen.add(Heineken);
    }

    @Override
    public List<IFonds> getKoersen() throws RemoteException {
        return fondsen;

    }

}
