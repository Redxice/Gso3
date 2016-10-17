/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author redxice
 */
public class EffectenBeursTimeTask extends TimerTask{
    private IEffectenbeurs effectenbeurs;
    private Random RD;
    public EffectenBeursTimeTask(IEffectenbeurs effectenbeurs){
        this.effectenbeurs =effectenbeurs ;
        RD = new Random();
    }
    
 
    @Override
    public void run() {
        try {
            for (IFonds fond : effectenbeurs.getKoersen()) {
                fond.setKoers(RD.nextDouble() * 100);
                System.out.println(fond.getKoers());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(EffectenBeursTimeTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
    
 

