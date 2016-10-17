package aex.banner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author redxice
 */

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BannerController extends AEXBanner {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private String BannerText;
   

    
    public BannerController(AEXBanner banner) {

        this.banner = banner;
        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        pollingTimer.schedule(new KoersTimeTask(this),0,2000);
    
    }
    
    public AEXBanner GetBanner(){
        return this.banner;
    }
    public String GetBannerText(){
        return BannerText;
    }
    public void Update() throws NotBoundException{
        BannerText="";
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
            IEffectenbeurs effectenbeurs = (IEffectenbeurs)registry.lookup("AEX");
            List<IFonds> fondsen = effectenbeurs.getKoersen();
            for (IFonds fond : fondsen) {
                BannerText +=fond.toString()+" ";
            }
        } catch (RemoteException ex) {
            BannerText = "AEX SERVER UNREACHABLE";
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    // Stop banner controller
    public void stop() {
       pollingTimer.cancel();
       MockEffectenbeurs Eb = (MockEffectenbeurs) effectenbeurs;
       Eb.stop();
    }
   
}

