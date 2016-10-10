/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.banner;

import java.rmi.NotBoundException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
      try {
          controller.Update();
          Platform.runLater(new Runnable(){
              @Override
              public void run(){
                  controller.GetBanner().setKoersen(controller.GetBannerText());
              }
              
          });
      } catch (NotBoundException ex) {
          Logger.getLogger(KoersTimeTask.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    }
}
