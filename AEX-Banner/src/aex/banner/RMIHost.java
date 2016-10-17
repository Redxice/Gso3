/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

import fontyspublisher.RemotePublisher;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daan
 */
public class RMIHost {


    public static void main(String[] args){
        RemotePublisher publisher;
        try {
            IEffectenbeurs effectenbeurs = new Effectenbeurs();
            
            publisher = new RemotePublisher();
            publisher.registerProperty("fondsen");
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AEX", publisher);
            Timer t = new Timer();
            t.schedule(new EffectenBeursTimeTask(effectenbeurs, publisher),0,2000);
            
        } catch (RemoteException ex) {
            Logger.getLogger(RMIHost.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
