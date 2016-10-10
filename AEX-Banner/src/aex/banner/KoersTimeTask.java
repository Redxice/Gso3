/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author redxice
 */
public class KoersTimeTask extends TimerTask {
  BannerController controller;
    public KoersTimeTask(BannerController controller){
        this.controller = controller;
    }


    @Override
    public void run() {
      controller.Update();
      Platform.runLater(new Runnable(){
        @Override
        public void run(){
         controller.GetBanner().setKoersen(controller.GetBannerText());
        }
            
        });
    
    }
}
