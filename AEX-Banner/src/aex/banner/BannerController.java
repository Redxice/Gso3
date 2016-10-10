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

import java.util.Timer;


public class BannerController extends AEXBanner {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private String BannerText;
   

    
    public BannerController(AEXBanner banner) {

        this.banner = banner;
        this.effectenbeurs = new MockEffectenbeurs();
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
    public void Update(){
        BannerText="";

        for (IFonds fond : effectenbeurs.getKoersen()) {
       
         BannerText +=fond.toString()+" ";
        }
    
    }
    // Stop banner controller
    public void stop() {
       pollingTimer.cancel();
       MockEffectenbeurs Eb = (MockEffectenbeurs) effectenbeurs;
       Eb.stop();
    }
   
}

