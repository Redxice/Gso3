/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

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
        try {
            IEffectenbeurs effectenbeurs = new Effectenbeurs();
            
            Registry registry;
            registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AEX", effectenbeurs);
            Timer t = new Timer();
            t.schedule(new EffectenBeursTimeTask(effectenbeurs),0,2000);
            
        } catch (RemoteException ex) {
            Logger.getLogger(RMIHost.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
